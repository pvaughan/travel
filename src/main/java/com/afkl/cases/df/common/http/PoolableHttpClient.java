package com.afkl.cases.df.common.http;

import com.afkl.cases.df.common.exception.HttpException;
import com.afkl.cases.df.model.StatisticModel;
import com.afkl.cases.df.service.StatisticService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * Created by pvaughan on 02/12/2016.
 */
@Service
public class PoolableHttpClient extends AbstractServerHttpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(PoolableHttpClient.class.getName());

    private final PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

    private HttpClient httpClient;

    @Value("${poolable-http.pool-size}")
    private int poolSize = 100;

    @Value("${poolable-http.max-per-route}")
    private int maxPerRoute = 50;

    private final StatisticService statisticService;


    @Autowired
    public PoolableHttpClient(StatisticService statisticService) {
        this.statisticService = statisticService;
        this.poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(poolSize);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
        HttpClientBuilder hcBuilder = HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager);
        httpClient = hcBuilder.build();
    }


    public String doRequest(RequestBuilder requestBuilder) throws HttpException {
        HttpResponse response = null;
        String responseContent = null;
        try {
            long startTime = System.currentTimeMillis();
            response = httpClient.execute(requestBuilder.build());

            statisticService.addNewStatistic(new StatisticModel(
                    new Date(),
                    System.currentTimeMillis() - startTime,
                    response.getStatusLine().getStatusCode())
            );

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
