package com.afkl.cases.df.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by pvaughan on 03/12/2016.
 */
@JsonAutoDetect
public class AuthToken {

    private String access_token;
    private String token_type;
    private Long expires_in;
    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
