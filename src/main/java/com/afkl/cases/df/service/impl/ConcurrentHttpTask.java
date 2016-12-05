package com.afkl.cases.df.service.impl;

import com.afkl.cases.df.common.exception.HttpException;
import com.afkl.cases.df.common.http.PoolableHttpJsonClient;
import com.afkl.cases.df.common.http.model.JsonRequest;
import com.afkl.cases.df.model.FareOffer;
import com.afkl.cases.df.service.AuthService;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by pvaughan on 05/12/2016.
 */
public class ConcurrentHttpTask implements Callable {

    private final PoolableHttpJsonClient httpJsonClient;
    private final String url;
    private final Map<String, String> headers;

    public ConcurrentHttpTask(PoolableHttpJsonClient httpJsonClient, String url, Map<String, String> headers) {
        this.httpJsonClient = httpJsonClient;
        this.url = url;
        this.headers = headers;
    }

    @Override
    public FareOffer call() throws Exception {
        return getFareOffers();
    }

    private FareOffer getFareOffers() {
        Map<String, String> params = new HashMap<String, String>();

        try {
            return this.getHttpJsonClient().doGet(
                    JsonRequest.newBuilder()
                            .url(this.getUrl())
                            .headers(this.getHeaders())
                            .params(params)
                            .build(),
                    new TypeReference<FareOffer>() {
                    }
            );
        } catch (HttpException e) {
            throw new RuntimeException(e);
        }
    }

    public PoolableHttpJsonClient getHttpJsonClient() {
        return httpJsonClient;
    }


    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}