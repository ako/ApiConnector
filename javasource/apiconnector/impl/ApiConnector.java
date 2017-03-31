package apiconnector.impl;

import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;

/**
 * Created by ako on 22-6-2016.
 */
public class ApiConnector {

    private static ApiConnectorHandler httpHandler = null;

    public ApiConnector() {
        if (httpHandler == null) {
            httpHandler = new ApiConnectorHandler();
            Core.addRequestHandler("api/", httpHandler);
        }
    }

    public void addHttpEndpoint(String url, String microflowName, Boolean supportsGET, Boolean supportsPOST) {
        httpHandler.addHttpEndpoint(url,
                new ApiEndpoint().
                        withUrl(url).
                        withMicroflowName(microflowName).
                        withSupportsGet(supportsGET).
                        withSupportsPost(supportsPOST));
    }

    public void setApiStateUnknownResource(IContext ctx) {
        httpHandler.setApiStateUnknownResource(ctx);
    }

    public void setApiHeaderLocation(IContext context, String locationPath) {
        httpHandler.setApiHeaderLocation(context, locationPath);
    }

    public void setApiStatusCode(IContext ctx, int statusCode) {
        httpHandler.setApiStatusCode(ctx, statusCode);
    }

    public void addHttpEndpoint(String urlPattern, String microflowName, String xpathQuery, String oqlQuery,
                                Boolean supportsGET, Boolean supportsPOST, Boolean supportsPUT, Boolean supportsDELETE, Boolean supportsPATCH,
                                String requestMappingName, String requestEntity, String responseMappingName, String responseEntity) {
        httpHandler.addHttpEndpoint(urlPattern,
                new ApiEndpoint().
                        withUrl(urlPattern).
                        withMicroflowName(microflowName).
                        withOqlQuery(oqlQuery).
                        withXpathQuery(xpathQuery).
                        withSupportsGet(supportsGET).
                        withSupportsPost(supportsPOST).
                        withSupportsPut(supportsPUT).
                        withSupportsDelete(supportsDELETE).
                        withSupportsPatch(supportsPATCH).
                        withRequestMapping(requestMappingName).
                        withRequestEntity(requestEntity).
                        withResponseMapping(responseMappingName).
                        withResponseEntity(responseEntity));
    }

    public  void addHttpEndpoint(ApiEndpoint apiEndpoint){
        httpHandler.addHttpEndpoint(apiEndpoint.getUrl(),apiEndpoint);
    }

    public void setApiResponseCacheControl(IContext context, String cacheControl) {
        httpHandler.setApiResponseCacheControl(context, cacheControl);
    }

    public void setApiResponseContentType(IContext context, String mimeType) {
        httpHandler.setApiResponseContentType(context, mimeType);
    }
}
