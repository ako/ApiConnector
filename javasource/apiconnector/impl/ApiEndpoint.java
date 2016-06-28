package apiconnector.impl;

import com.mendix.systemwideinterfaces.core.IMendixObject;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by ako on 22-6-2016.
 */
public class ApiEndpoint {
    private Pattern urlMatcher;
    private Boolean supportsGet = false;
    private Boolean supportsPost = false;
    private Boolean supportsPut = false;
    private Boolean supportsDelete = false;
    private Boolean supportsPatch = false;
    private String url;
    private String microflowName;
    private Pattern urlParameterGroupMatcher;
    private String[] parameterNames;
    private String requestMappingName;
    private String requestEntity;
    private String responseMappingName;
    private String responseEntity;

    public Boolean getSupportsPut() {
        return supportsPut;
    }

    public void setSupportsPut(Boolean supportsPut) {
        this.supportsPut = supportsPut;
    }

    public Boolean getSupportsDelete() {
        return supportsDelete;
    }

    public void setSupportsDelete(Boolean supportsDelete) {
        this.supportsDelete = supportsDelete;
    }

    public Boolean getSupportsPatch() {
        return supportsPatch;
    }

    public void setSupportsPatch(Boolean supportsPatch) {
        this.supportsPatch = supportsPatch;
    }

    @Override
    public String toString() {
        return "ApiEndpoint{" +
                "urlMatcher=" + urlMatcher +
                ", supportsGet=" + supportsGet +
                ", supportsPost=" + supportsPost +
                ", supportsPut=" + supportsPut +
                ", supportsDelete=" + supportsDelete +
                ", supportsPatch=" + supportsPatch +
                ", url='" + url + '\'' +
                ", microflowName='" + microflowName + '\'' +
                ", urlParameterGroupMatcher=" + urlParameterGroupMatcher +
                ", parameterNames=" + Arrays.toString(parameterNames) +
                ", requestMappingName='" + requestMappingName + '\'' +
                ", requestEntity='" + requestEntity + '\'' +
                ", responseMappingName='" + responseMappingName + '\'' +
                ", responseEntity='" + responseEntity + '\'' +
                '}';
    }

    public String getMethodsString(){
        return (supportsGet?"GET":"") +
                (supportsPost?"POST":"") +
                (supportsPut?"PUT":"") +
                (supportsDelete?"DELETE":"") +
                (supportsPatch?"PATCH":"");
    }

    public Boolean getSupportsGet() {
        return supportsGet;
    }

    public void setSupportsGet(Boolean supportsGet) {
        this.supportsGet = supportsGet;
    }

    public Boolean getSupportsPost() {
        return supportsPost;
    }

    public void setSupportsPost(Boolean supportsPost) {
        this.supportsPost = supportsPost;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMicroflowName() {
        return microflowName;
    }

    public void setMicroflowName(String microflowName) {
        this.microflowName = microflowName;
    }


    public ApiEndpoint withUrl(String url) {
        this.url = url;
        return this;
    }

    public ApiEndpoint withMicroflowName(String microflowName) {
        this.microflowName = microflowName;
        return this;
    }

    public ApiEndpoint withSupportsGet(Boolean supportsGET) {
        this.supportsGet = supportsGET;
        return this;
    }


    public ApiEndpoint withSupportsPost(Boolean supportsPOST) {
        this.supportsPost = supportsPOST;
        return this;
    }

    public void setUrlMatcher(Pattern urlMatcher) {
        this.urlMatcher = urlMatcher;
    }

    public Pattern getUrlMatcher() {
        return urlMatcher;
    }

    public void setUrlParameterGroupMatcher(Pattern urlParameterGroupMatcher) {
        this.urlParameterGroupMatcher = urlParameterGroupMatcher;
    }

    public Pattern getUrlParameterGroupMatcher() {
        return urlParameterGroupMatcher;
    }

    public String getParameterName(int i) {
        return parameterNames[i];
    }

    public void setParameterNames(String[] parameterNames) {
        this.parameterNames = parameterNames;
    }

    public String[] getParameterNames() {
        return parameterNames;
    }


    public String getRequestMappingName() {
        return requestMappingName;
    }

    public void setRequestMappingName(String requestMappingName) {
        this.requestMappingName = requestMappingName;
    }

    public String getRequestEntity() {
        return requestEntity;
    }

    public void setRequestEntity(String requestEntity) {
        this.requestEntity = requestEntity;
    }

    public String getResponseMappingName() {
        return responseMappingName;
    }

    public void setResponseMappingName(String responseMappingName) {
        this.responseMappingName = responseMappingName;
    }

    public String getResponseEntity() {
        return responseEntity;
    }

    public void setResponseEntity(String responseEntity) {
        this.responseEntity = responseEntity;
    }


    public ApiEndpoint withRequestMapping(String requestMappingName) {
        this.requestMappingName = requestMappingName;
        return this;
    }

    public ApiEndpoint withRequestEntity(IMendixObject requestEntity) {
        this.requestEntity = (requestEntity != null ? requestEntity.getMetaObject().getName() : null);
        return this;
    }

    public ApiEndpoint withResponseMapping(String responseMappingName) {
        this.responseMappingName = responseMappingName;
        return this;
    }

    public ApiEndpoint withResponseEntity(IMendixObject responseEntity) {
        this.responseEntity = (responseEntity != null ? responseEntity.getMetaObject().getName() : null);
        return this;
    }

    public ApiEndpoint withSupportsPut(Boolean supportsPUT) {
        this.supportsPut = supportsPUT;
        return this;
    }

    public ApiEndpoint withSupportsDelete(Boolean supportsDELETE) {
        this.supportsDelete = supportsDELETE;
        return this;
    }

    public ApiEndpoint withSupportsPatch(Boolean supportsPATCH) {
        this.supportsPatch = supportsPATCH;
        return this;
    }
}
