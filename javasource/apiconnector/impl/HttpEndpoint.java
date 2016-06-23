package apiconnector.impl;

import java.util.regex.Pattern;

/**
 * Created by ako on 22-6-2016.
 */
public class HttpEndpoint {
    private Pattern urlMatcher;
    private Boolean supportsGet;
    private Boolean supportsPost;
    private String url;
    private String microflowName;
    private Pattern urlParameterGroupMatcher;
    private String[] parameterNames;

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


    public HttpEndpoint withUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpEndpoint withMicroflowName(String microflowName) {
        this.microflowName = microflowName;
        return this;
    }

    public HttpEndpoint withSupportsGet(Boolean supportsGET) {
        this.supportsGet = supportsGET;
        return this;
    }


    public HttpEndpoint withSupportsPost(Boolean supportsPOST) {
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
}
