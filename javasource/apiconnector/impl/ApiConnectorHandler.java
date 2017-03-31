package apiconnector.impl;

import com.google.common.io.ByteStreams;
import com.mendix.core.Core;
import com.mendix.externalinterface.connector.RequestHandler;
import com.mendix.logging.ILogNode;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.connectionbus.data.IDataRow;
import com.mendix.systemwideinterfaces.connectionbus.data.IDataTable;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.ISession;
import com.mendix.systemwideinterfaces.core.IUser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by ako on 22-6-2016.
 */
public class ApiConnectorHandler extends RequestHandler {
    private static HashMap<String, ApiEndpoint> registeredHttpEndpoints = new HashMap<String, ApiEndpoint>();
    private static ILogNode logger = Core.getLogger(ApiConnectorHandler.class.getName());
    private static Pattern urlToRe = Pattern.compile("\\{[a-zA-Z0-9_-]*\\}");
    private static Pattern urlParsToRe = Pattern.compile("(\\{[a-zA-Z0-9_-]*\\})");
    private static HashMap<String, ApiMicroflowState> apiState = new HashMap<String, ApiMicroflowState>();

    @Override
    protected void processRequest(IMxRuntimeRequest iMxRuntimeRequest, IMxRuntimeResponse iMxRuntimeResponse, String s) throws Exception {
        logger.info("> processRequest");

        Iterator<ApiEndpoint> endpointIter = registeredHttpEndpoints.values().iterator();
        String queryString = iMxRuntimeRequest.getHttpServletRequest().getQueryString();
        String apiPath = iMxRuntimeRequest.getHttpServletRequest().getRequestURI();
        String method = iMxRuntimeRequest.getHttpServletRequest().getMethod();

        if (queryString != null) {
            apiPath = apiPath + "?" + queryString;
        }
        logger.info(String.format("apipath = %s %s", method, apiPath));

        if (apiPath.equals("/api/swagger.json")) {
            logger.info("Generating swagger spec");
            iMxRuntimeResponse.setContentType("application/json");
            SwaggerGenerator swaggerGenerator = new SwaggerGenerator(registeredHttpEndpoints);
            swaggerGenerator.writeSpec(iMxRuntimeResponse.getWriter());
            return;
        }

        while (endpointIter.hasNext()) {
            ApiEndpoint endpoint = endpointIter.next();
            if (endpoint.getUrlMatcher().matcher(apiPath).matches() &&
                    ((endpoint.getSupportsGet() && method.equals("GET"))
                            || (endpoint.getSupportsPost() && method.equals("POST"))
                            || (endpoint.getSupportsPut() && method.equals("PUT"))
                            || (endpoint.getSupportsDelete() && method.equals("DELETE"))
                            || (endpoint.getSupportsPatch() && method.equals("PATCH"))
                    )) {
                logger.info(String.format("request handler path %s matches %s", apiPath, endpoint.getUrlMatcher().toString()));
                IContext ctx = null;
                ISession session = null;
                IUser user = null;
                logger.info(String.format("Auth-type: %s", iMxRuntimeRequest.getHttpServletRequest().getAuthType()));
                logger.info(String.format("User principal: %s", iMxRuntimeRequest.getHttpServletRequest().getUserPrincipal()));
                logger.info(String.format("Remote user: %s", iMxRuntimeRequest.getHttpServletRequest().getRemoteUser()));
                try {
                    if (iMxRuntimeRequest.getHeader("username") != null && iMxRuntimeRequest.getHeader("password") != null) {
                        String username = iMxRuntimeRequest.getHeader("username");
                        String password = iMxRuntimeRequest.getHeader("password");
                        logger.info(String.format("Logging in with username %s", username));
                        session = Core.login(username, password);
                        ctx = session.createContext();
                    } /* else if () {
                        String username = iMxRuntimeRequest.getHeader("username");
                        String password = iMxRuntimeRequest.getHeader("password");
                        logger.info(String.format("Logging in with username %s", username));
                        session = Core.login(username, password);
                        ctx = session.createContext();

                    } */ else if (iMxRuntimeRequest.getHeader("token") != null) {
                        String username = iMxRuntimeRequest.getHeader("username");
                        String token = iMxRuntimeRequest.getHeader("token");
                        logger.info(String.format("Logging in with username %s, token: %s", username, token));
                        ctx = Core.createSystemContext().createSudoClone();
                        user = Core.getUser(ctx, username);
                        session = Core.initializeSession(user, null);
                        //session.getLanguage()
                    } else {
                        ctx = Core.createSystemContext();
                        session = ctx.getSession();
                    }

                    logger.info(String.format("Calling onMessage microflow: %s", endpoint.getMicroflowName()));

                    /*
                     * Get request payload as string
                     */
                    String stringPayload = null;
                    IMendixObject objectPayload = null;
                    if (endpoint.getRequestMappingName() == null) {
                        stringPayload = new BufferedReader(new InputStreamReader(iMxRuntimeRequest.getInputStream()))
                                .lines().collect(Collectors.joining("\n"));
                    } else {
                        List<IMendixObject> objs = Core.importStream(ctx, iMxRuntimeRequest.getInputStream(),
                                endpoint.getRequestMappingName(), null, false);
                        logger.info("Import mapping result: " + objs.size());
                        objectPayload = objs.get(0);
                    }

                    /*
                     * get parameter values
                     */
                    Matcher parMatcher = endpoint.getUrlParameterGroupMatcher().matcher(apiPath);
                    int groupCount = parMatcher.groupCount();
                    logger.info(String.format("Parameter groups found: %d", groupCount));
                    Map pars = new HashMap();
                    if (parMatcher.find()) {
                        for (int i = 1; i <= groupCount; i++) {
                            String parValue = parMatcher.group(i);
                            String parName = endpoint.getParameterName(i - 1);
                            logger.info(String.format("group: %s = %s", parName, parValue));
                            pars.put(parName, parValue);
                        }
                    }

                    /*
                     * Set microflow parameters
                     */
                    pars.put("Url", iMxRuntimeRequest.getResourcePath());
                    pars.put("Operation", iMxRuntimeRequest.getHttpServletRequest().getMethod());
                    if (endpoint.getRequestMappingName() == null) {
                        pars.put("Payload", (stringPayload != null ? stringPayload : null));
                    } else {
                        logger.info("setting payload object: " + objectPayload.toString());
                        pars.put("Payload", (objectPayload != null ? objectPayload : null));
                    }
                    pars.put("ContentType", (iMxRuntimeRequest.getHttpServletRequest().getContentType() != null ? iMxRuntimeRequest.getHttpServletRequest().getContentType() : ""));

                    logger.info("Parameter map: " + pars);

                    /*
                     * Initialize API state
                     */
                    ctx.startTransaction();
                    String txId = ctx.getTransactionId().toString();
                    logger.info("processRequest: txId = " + txId);
                    ApiMicroflowState mfState = new ApiMicroflowState();
                    mfState.setHeaderLocation(apiPath);
                    apiState.put(txId, mfState);
                    mfState.setResponseMimeType("application/json");

                    String resultString = null;
                    Object resultObject = null;
                    Object resultBytes = null;
                    if (endpoint.getMicroflowName() != null) {
                        /*
                         * Invoke microflow
                         */
                        if (endpoint.getResponseMappingName() == null && !endpoint.getBinaryResponse()) {
                            resultString = (String) Core.execute(ctx, endpoint.getMicroflowName(), true, pars);
                        } else if (endpoint.getBinaryResponse()) {
                            resultObject = Core.execute(ctx, endpoint.getMicroflowName(), true, pars);
                        } else {
                            resultObject = Core.execute(ctx, endpoint.getMicroflowName(), true, pars);
                        }
                    } else if (endpoint.getXpathQuery() != null) {
                        /**
                         * Xpath endpoint
                         */
                        Map<String, String> sort = new HashMap<String, String>();
                        resultObject = Core.retrieveXPathQuery(ctx, endpoint.getXpathQuery(), 1, 0, sort).get(0);
                        logger.info(String.format("Xpath %s result %s", endpoint.getXpathQuery(), resultObject.toString()));
                    } else if (endpoint.getOqlQuery() != null) {
                        /**
                         * Oql endpoint
                         */
                        logger.info(String.format("executing OQL query: %s", endpoint.getOqlQuery()));
                        //Core.oq
                        IDataTable table = Core.retrieveOQLDataTable(ctx, endpoint.getOqlQuery());
                        Iterator<IDataRow> rowIter = table.iterator();
                        while (rowIter.hasNext()) {
                            IDataRow row = rowIter.next();
                            IMendixObject obj = Core.instantiate(ctx, endpoint.getResponseEntity());
                            int cols = row.getSchema().getColumnCount();
                            for (int i = 0; i < cols; i++) {
                                String colName = row.getSchema().getColumnSchema(i).getName();
                                obj.setValue(ctx, colName, row.getValue(ctx, i));
                            }
                            resultObject = obj;
                        }

                    }
                    /*
                     * Handle API state on response
                     */
                    if (apiState.containsKey(txId)) {
                        iMxRuntimeResponse.setStatus(apiState.get(txId).getStatusCode());
                        iMxRuntimeResponse.addHeader("Location", apiState.get(txId).getHeaderLocation());
                        if (apiState.get(txId).getResponseMimeType() != null) {
                            iMxRuntimeResponse.setContentType(apiState.get(txId).getResponseMimeType());
                        }
                        if (apiState.get(txId).getResponseCacheControl() != null) {
                            iMxRuntimeResponse.addHeader("cache-control", apiState.get(txId).getResponseCacheControl());
                        }
                    } else {
                        logger.info(String.format("Api state not found for %s, %s", endpoint.getMicroflowName(), txId));
                    }
                    /**
                     * Return request response
                     */
                    if (endpoint.getResponseMappingName() == null) {
                        if (resultObject != null && endpoint.getBinaryResponse()) {
                            //iMxRuntimeResponse.setContentType("image/jpeg");
                            InputStream is = Core.getImage(ctx, (IMendixObject) resultObject, false);
                            OutputStream os = iMxRuntimeResponse.getOutputStream();
                            ByteStreams.copy(is, os);
                            is.close();
                            os.close();
                        }
                        if (resultString != null) {
                            iMxRuntimeResponse.getWriter().write(resultString);
                        }
                    } else {
                        if (resultObject != null && resultObject instanceof IMendixObject) {
                            logger.info("Mapping result object: " + resultObject.toString());
                            InputStream is = Core.exportStream(ctx, endpoint.getResponseMappingName(), (IMendixObject) resultObject, false);
                            OutputStream os = iMxRuntimeResponse.getOutputStream();
                            ByteStreams.copy(is, os);
                            is.close();
                            os.close();
                        } else if (resultObject == null) {
                            //iMxRuntimeResponse.getWriter().write("{'error':'No data found'}");
                        } else {
                            iMxRuntimeResponse.getWriter().write("{'error':'Exporting complex json not yet supported'}");
                        }
                    }
//                    apiState.put(txId, null);
                    apiState.remove(txId);
                    ctx.endTransaction();
                    try {
                        Core.logout(session);
                    } catch (Exception e1) {
                        // you cannot logout if not logged in, ignore
                        logger.warn(e1.getMessage());
                    }
                    break;
                } catch (Exception e) {
                    logger.warn(String.format("Failed to execute microflow: %s", endpoint.getMicroflowName()));
                    throw (e);
                }
            } else {
                logger.info(String.format("request handler path %s does not match %s, %s", apiPath, endpoint.getUrlMatcher().toString(), endpoint.toString()));
            }
        }
        logger.info("< processRequest");
    }

    /**
     * Add endpoint to Http Request Handler
     *
     * @param url
     * @param endpoint
     */
    public void addHttpEndpoint(String url, ApiEndpoint endpoint) {
        logger.info(String.format("addHttpEndpoint: %s, %s", url, endpoint));

        String urlRe = urlToRe.matcher(url).replaceAll("\\[a-zA-Z0-9_-\\]*");
        String urlParGroupsRe = urlToRe.matcher(url).replaceAll("(\\[a-zA-Z0-9_-\\]*)");

        /*
         * find parameter names
         */
        ArrayList<String> parNames = new ArrayList<String>();
        Matcher parMatcher = urlParsToRe.matcher(url);

        logger.info(String.format("url matcher re: %s", urlRe));
        logger.info(String.format("url parameter group matcher re: %s", urlParGroupsRe));
        logger.info(String.format("parMatcher: %s", parMatcher));
        while (parMatcher.find()) {
            logger.info(String.format("found parameters: %d", parMatcher.groupCount()));
            for (int i = 0; i < parMatcher.groupCount(); i++) {
                String parName = parMatcher.group(i).replace("{", "").replace("}", "");
                logger.info(String.format("Found parameter: %s", parName));
                parNames.add(parName);
            }
        }

        logger.info(String.format("parameter names: %s", parNames.toString()));

        endpoint.setUrlParameterGroupMatcher(Pattern.compile(urlParGroupsRe));
        endpoint.setUrlMatcher(Pattern.compile(urlRe));
        endpoint.setParameterNames(parNames.toArray(new String[0]));

        registeredHttpEndpoints.put(url + endpoint.getMethodsString(), endpoint);
    }

    public void setApiStateUnknownResource(IContext ctx) {
        ApiMicroflowState mfState = getApiStateForThread(ctx);
        mfState.setStatusCode(404);
    }

    private ApiMicroflowState getApiStateForThread(IContext ctx) {
        String txId = ctx.getTransactionId().toString();
        logger.info("getApiStateForThread: tx = " + txId);
        ApiMicroflowState mfState = null;
        if (!apiState.containsKey(txId)) {
            mfState = new ApiMicroflowState();
            apiState.put(txId, mfState);
        } else {
            mfState = apiState.get(txId);
        }
        return mfState;
    }

    public void setApiHeaderLocation(IContext context, String locationPath) {
        ApiMicroflowState mfState = getApiStateForThread(context);
        mfState.setHeaderLocation(locationPath);
    }

    public void setApiStatusCode(IContext ctx, int statusCode) {
        ApiMicroflowState mfState = getApiStateForThread(ctx);
        mfState.setStatusCode(statusCode);
    }

    public void setApiResponseCacheControl(IContext context, String cacheControl) {
        ApiMicroflowState mfState = getApiStateForThread(context);
        mfState.setResponseCacheControl(cacheControl);
    }

    public void setApiResponseContentType(IContext context, String mimeType) {
        ApiMicroflowState mfState = getApiStateForThread(context);
        mfState.setResponseMimeType(mimeType);
    }
}
