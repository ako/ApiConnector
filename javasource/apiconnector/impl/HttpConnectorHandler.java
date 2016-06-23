package apiconnector.impl;

import com.google.inject.internal.ImmutableMap;
import com.mendix.core.Core;
import com.mendix.externalinterface.connector.RequestHandler;
import com.mendix.logging.ILogNode;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.ISession;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by ako on 22-6-2016.
 */
public class HttpConnectorHandler extends RequestHandler {
    private static HashMap<String, HttpEndpoint> registeredHttpEndpoints = new HashMap<String, HttpEndpoint>();
    private static ILogNode logger = Core.getLogger(HttpConnectorHandler.class.getName());
    private static Pattern urlToRe = Pattern.compile("\\{[a-zA-Z0-9_-]*\\}");
    private static Pattern urlParsToRe = Pattern.compile("(\\{[a-zA-Z0-9_-]*\\})");

    @Override
    protected void processRequest(IMxRuntimeRequest iMxRuntimeRequest, IMxRuntimeResponse iMxRuntimeResponse, String s) throws Exception {
        logger.info("processRequest");

        Iterator<HttpEndpoint> endpointIter = registeredHttpEndpoints.values().iterator();
        String queryString = iMxRuntimeRequest.getHttpServletRequest().getQueryString();
        String apiPath = iMxRuntimeRequest.getHttpServletRequest().getRequestURI();
        if(queryString != null){
            apiPath = apiPath + "?" + queryString;
        }
        logger.info(String.format("apipath = %s", apiPath));

        while (endpointIter.hasNext()) {
            HttpEndpoint endpoint = endpointIter.next();
            if (endpoint.getUrlMatcher().matcher(apiPath).matches()) {
                logger.info(String.format("request handler path %s matches %s", apiPath, endpoint.getUrlMatcher().toString()));

                try {
                    IContext ctx = Core.createSystemContext();
                    ISession session = ctx.getSession();
                    logger.info(String.format("Calling onMessage microflow: %s", endpoint.getMicroflowName()));
                    /*
                     * Get request payload as string
                     */
                    String payload = new BufferedReader(new InputStreamReader(iMxRuntimeRequest.getInputStream()))
                            .lines().collect(Collectors.joining("\n"));

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
                    pars.put("Payload", (payload != null ? payload : ""));
                    pars.put("ContentType", (iMxRuntimeRequest.getHttpServletRequest().getContentType() != null ? iMxRuntimeRequest.getHttpServletRequest().getContentType() : ""));

                    logger.info("Parameter map: " + pars);

                    /*
                     * Invoke microflow
                     */
                    String result = (String) Core.execute(ctx, endpoint.getMicroflowName(), true, pars);

                    /*
                     * Return request response
                     */
                    if (result != null) {
                        iMxRuntimeResponse.getWriter().write(result);
                    }
                    break;
                } catch (Exception e) {
                    logger.warn(String.format("Failed to execute microflow: %s", endpoint.getMicroflowName()));
                    throw (e);
                }
            } else {
                logger.info(String.format("request handler path %s does not matches %s", apiPath, endpoint.getUrlMatcher().toString()));
            }
        }

    }

    public void addHttpEndpoint(String url, HttpEndpoint endpoint) {
        logger.info(String.format("addHttpEndpoint: %s, %s", url, endpoint));

        String urlRe = urlToRe.matcher(url).replaceAll("\\[a-zA-Z0-9_-\\]*");
        String urlParGroupsRe = urlToRe.matcher(url).replaceAll("(\\[a-zA-Z0-9_-\\]*)");

        /*
         * find parameter names
         */
        ArrayList<String> parNames = new ArrayList<String>();
        Matcher parMatcher = urlParsToRe.matcher(url);
        if (parMatcher.find()) {
            logger.info(String.format("found parameters: %d", parMatcher.groupCount()));
            for (int i = 0; i < parMatcher.groupCount(); i++) {
                String parName = parMatcher.group(i).replace("{", "").replace("}", "");
                logger.info(String.format("Found parameter: %s", parName));
                parNames.add(parName);
            }
        }

        logger.info(String.format("parameter names: %s", parNames.toString()));
        logger.info(String.format("url matcher re: %s", urlRe));
        logger.info(String.format("url parameter group matcher re: %s", urlParGroupsRe));

        endpoint.setUrlParameterGroupMatcher(Pattern.compile(urlParGroupsRe));
        endpoint.setUrlMatcher(Pattern.compile(urlRe));
        endpoint.setParameterNames(parNames.toArray(new String[0]));

        registeredHttpEndpoints.put(url, endpoint);
    }
}
