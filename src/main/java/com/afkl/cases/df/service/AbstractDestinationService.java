package com.afkl.cases.df.service;

import com.afkl.cases.df.common.http.PoolableHttpJsonClient;
import com.afkl.cases.df.model.AuthToken;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pvaughan on 03/12/2016.
 */
public abstract class AbstractDestinationService {

    @Value("${travel-api.base-url}")
    private String BASE_URL;

    private final PoolableHttpJsonClient httpJsonClient;
    private final AuthService authService;

    public AbstractDestinationService(PoolableHttpJsonClient poolableHttpClient, AuthService authService) {
        this.httpJsonClient = poolableHttpClient;
        this.authService = authService;
    }

    protected String getServerBaseAddress(){
        return BASE_URL;
    }

    protected PoolableHttpJsonClient getHttpJsonClient() {
        return httpJsonClient;
    }

    protected Map<String, String> getSecurityHeader() {
        AuthToken token = this.getAuthService().getAuthToken();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", token.getToken_type() + " " + token.getAccess_token());
        headers.put("Content-Type", "application/json");
        return headers;
    }

    protected AuthService getAuthService() {
        return authService;
    }

}