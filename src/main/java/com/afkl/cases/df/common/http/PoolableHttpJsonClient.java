package com.afkl.cases.df.common.http;

import com.afkl.cases.df.common.exception.HttpException;
import com.afkl.cases.df.common.http.model.JsonRequest;
import com.afkl.cases.df.service.StatisticService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by pvaughan on 02/12/2016.
 */
@Service
public class PoolableHttpJsonClient {


    private final AbstractServerHttpClient poolableHttpClient;

    private final ObjectMapper objectMapper;

    @Autowired
    public PoolableHttpJsonClient(AbstractServerHttpClient poolableHttpClient) {
        this.poolableHttpClient = poolableHttpClient;
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T doDelete(JsonRequest jsonRequest, TypeReference<T> responseType) throws HttpException {
        RequestBuilder requestBuilder = RequestBuilder.delete();
        return doRequest(requestBuilder, jsonRequest, responseType);
    }

    public <T> T doPut(JsonRequest jsonRequest, TypeReference<T> responseType) throws HttpException {
        RequestBuilder requestBuilder = RequestBuilder.put();
        return doRequest(requestBuilder, jsonRequest, responseType);
    }

    public <T> T doPost(JsonRequest jsonRequest, TypeReference<T> responseType) throws HttpException {
        RequestBuilder requestBuilder = RequestBuilder.post();
        return doRequest(requestBuilder, jsonRequest, responseType);
    }

    public <T> T doGet(JsonRequest jsonRequest, TypeReference<T> responseType) throws HttpException {
        RequestBuilder requestBuilder = RequestBuilder.get();
        return doRequest(requestBuilder, jsonRequest, responseType);
    }

    private <T> T doRequest(RequestBuilder requestBuilder, JsonRequest jsonRequest, TypeReference<T> responseType) throws HttpException {
        try {
            String objectAsJson = objectMapper.writeValueAsString(jsonRequest.getObject());

            requestBuilder.setUri(jsonRequest.getUrl());
            requestBuilder.setEntity(new StringEntity(objectAsJson, StandardCharsets.UTF_8));

            for (final Map.Entry<String, String> headerEntry : jsonRequest.getHeaders().entrySet()) {
                requestBuilder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }

            for (final Map.Entry<String, String> paramEntry : jsonRequest.getParams().entrySet()) {
                requestBuilder.addParameter(paramEntry.getKey(), paramEntry.getValue());
            }

            requestBuilder.addHeader("Connection","close");

            String response = poolableHttpClient.doRequest(requestBuilder);

            if (response == null || responseType == null) {
                return null;
            }

            return objectMapper.readValue(response, responseType);
        } catch (Exception e) {
            throw new HttpException(e);
        }
    }

}

