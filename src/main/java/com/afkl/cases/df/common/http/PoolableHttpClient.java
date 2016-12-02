package com.afkl.cases.df.common.http;

import com.afkl.cases.df.common.exception.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by pvaughan on 02/12/2016.
 */
@Service
public class PoolableHttpClient extends AbstractServerHttpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(PoolableHttpClient.class.getName());

    private final PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

    private HttpClient httpClient;

    public PoolableHttpClient() {
        poolingHttpClientConnectionManager = null;
        HttpClientBuilder hcBuilder = HttpClientBuilder.create().setConnectionManager(new BasicHttpClientConnectionManager());
        LOGGER.info("new single");
        httpClient = hcBuilder.build();
    }

    public PoolableHttpClient(int poolSize, int maxPerRoute) {
        this.poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(poolSize);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
        HttpClientBuilder hcBuilder = HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager);
        LOGGER.info("new PoolableHttpClient");
        httpClient = hcBuilder.build();
    }

    public String doRequest(RequestBuilder requestBuilder) throws HttpException {
        HttpResponse response = null;
        String responseContent = null;
        try {
            response = httpClient.execute(requestBuilder.build());
            if (response.getEntity() != null) {
                responseContent = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            return throwException(requestBuilder, response, e);
        }
        int statusCode = response.getStatusLine().getStatusCode();
        if (!(199 < statusCode && statusCode < 300)) {
            return throwException(requestBuilder, response, responseContent);
        }
        return responseContent;
    }

    public void shutDown() {
        LOGGER.debug("Shutting down PoolableHttpClient.");
        if (poolingHttpClientConnectionManager != null) {
            poolingHttpClientConnectionManager.close();
        }
    }

}
