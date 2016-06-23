package apiconnector.impl;

/**
 * Created by ako on 23-6-2016.
 */
public class ApiMicroflowState {
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    private int statusCode = 200;
    public ApiMicroflowState(){

    }
}
