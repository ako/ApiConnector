package apiconnector.impl;

import com.mendix.core.Core;

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

    public void setApiStateUnknownResource(){
        httpHandler.setApiStateUnknownResource();
    }
}
