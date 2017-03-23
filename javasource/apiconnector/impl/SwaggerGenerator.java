package apiconnector.impl;

import com.mendix.thirdparty.org.json.JSONException;
import com.mendix.thirdparty.org.json.JSONObject;

import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ako on 27-6-2016.
 */
public class SwaggerGenerator {
    private HashMap<String, ApiEndpoint> endpoints = null;

    public SwaggerGenerator(HashMap<String, ApiEndpoint> registeredHttpEndpoints) {
        this.endpoints = registeredHttpEndpoints;
    }

    public void writeSpec(Writer writer) throws JSONException {
        JSONObject specRoot = new JSONObject();
        specRoot.put("swagger","2.0");
        /*
         * Info object
         */
        JSONObject info = new JSONObject();
        info.put("title", "App api");
        specRoot.put("info", info);

        /*
         * paths
         */
        HashMap<String, JSONObject> paths = new HashMap<String, JSONObject>();
        Set<String> keys = endpoints.keySet();
        Iterator<String> keysIter = keys.iterator();
        while (keysIter.hasNext()) {
            String key = keysIter.next();
            JSONObject pathObject = new JSONObject();
            ApiEndpoint endpoint = endpoints.get(key);
            paths.put(endpoint.getUrl(), pathObject);
            if (endpoint.getSupportsGet()){
                JSONObject methodObject = new JSONObject();
                pathObject.put("get",methodObject);
            }

            // methods
        }
        specRoot.put("paths", paths);

        /*
         * Write result
         */
        specRoot.write(writer);

    }
}
