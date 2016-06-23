package apiconnector.impl;

import com.mendix.core.Core;
import com.mendix.externalinterface.connector.RequestHandler;

import java.util.HashMap;

/**
 * Created by ako on 22-6-2016.
 */
public class HttpConnector {

    private static HttpConnectorHandler httpHandler = null;

    public HttpConnector() {
        if (httpHandler == null) {
            httpHandler = new HttpConnectorHandler();
            Core.addRequestHandler("api/", httpHandler);
        }
    }

    public void addHttpEndpoint(String url, String microflowName, Boolean supportsGET, Boolean supportsPOST) {
        httpHandler.addHttpEndpoint(url,
                new HttpEndpoint().
                        withUrl(url).
                        withMicroflowName(microflowName).
                        withSupportsGet(supportsGET).
                        withSupportsPost(supportsPOST));
    }
}
