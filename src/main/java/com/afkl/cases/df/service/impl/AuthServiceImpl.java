package com.afkl.cases.df.service.impl;

import com.afkl.cases.df.common.exception.HttpException;
import com.afkl.cases.df.common.http.PoolableHttpJsonClient;
import com.afkl.cases.df.common.http.model.JsonRequest;
import com.afkl.cases.df.model.AuthToken;
import com.afkl.cases.df.service.AbstractDestinationService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pvaughan on 03/12/2016.
 */
@Service
public class AuthServiceImpl extends AbstractDestinationService implements com.afkl.cases.df.service.AuthService {

    @Value("${travel-api.auth}")
    private String OAUTH_URL;

    @Value("${travel-api.client-id:}")
    private String CLIENT_ID;

    @Value("${travel-api.secret}")
    private String CLIENT_SECRET;

    private final String BASIC_AUTH = "Basic %s";

    @Autowired
    public AuthServiceImpl(PoolableHttpJsonClient poolableHttpClient) {
        super(poolableHttpClient, null);
    }

    @Override
    public AuthToken getAuthToken(){
        Map<String, String> params = new HashMap<String, String>();

        try {
            return getHttpJsonClient().doPost(
                    JsonRequest.newBuilder()
                            .url(this.getServerBaseAddress() + OAUTH_URL)
                            .headers(getHeaders())
                            .params(params)
                            .build(),
                    new TypeReference<AuthToken>() {
                    }
            );
        } catch (HttpException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", this.getAuthString());
        headers.put("Content-Type", "application/json");
        return headers;
    }

    private String getAuthString(){
        final String auth = this.getClientId() + ":" + this.getClientSecret();
        final String encodedAuth = new String(Base64.encodeBase64(auth.getBytes()));
        return String.format(BASIC_AUTH, encodedAuth);
    }

    protected String getClientId() {
        return CLIENT_ID;
    }

    protected String getClientSecret() {
        return CLIENT_SECRET;
    }
}
