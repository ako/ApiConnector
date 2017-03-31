package apiconnector.impl;

/**
 * Created by ako on 23-6-2016.
 */
public class ApiMicroflowState {
    private String headerLocation;

    public String getResponseCacheControl() {
        return responseCacheControl;
    }

    private String responseCacheControl;
    private String responseMimeType;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    private int statusCode = 200;

    public ApiMicroflowState() {

    }

    public void setHeaderLocation(String headerLocation) {
        this.headerLocation = headerLocation;
    }

    public String getHeaderLocation() {
        return headerLocation;
    }

    public void setResponseCacheControl(String responseCacheControl) {
        this.responseCacheControl = responseCacheControl;
    }

    public void setResponseMimeType(String responseMimeType) {
        this.responseMimeType = responseMimeType;
    }

    public String getResponseMimeType() {
        return responseMimeType;
    }
}
