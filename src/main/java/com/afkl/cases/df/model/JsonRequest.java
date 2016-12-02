package com.afkl.cases.df.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pvaughan on 02/12/2016.
 */
public final class JsonRequest {

    public static final Map<String, String> JSON_HEADERS = new HashMap<String, String>();
    static {
        JSON_HEADERS.put("Content-Type", "application/json");
        JSON_HEADERS.put("Accept", "application/json");
    }

    private JsonRequest() {}

    private String url;
    private Map<String, String> params;
    private Map<String, String> headers;
    private Object object;

    private JsonRequest(Builder builder) {
        url = builder.url;
        params = builder.params;
        headers = builder.headers;
        object = builder.object;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Object getObject() {
        return object;
    }

    public static final class Builder {
        private String url;
        private Map<String, String> params = new HashMap<String, String>();
        private Map<String, String> headers = new HashMap<String, String>();
        private Object object;

        private Builder() {
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder params(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder object(Object object) {
            this.object = object;
            return this;
        }

        public JsonRequest build() {
            this.headers.putAll(JSON_HEADERS);
            return new JsonRequest(this);
        }
    }
}

