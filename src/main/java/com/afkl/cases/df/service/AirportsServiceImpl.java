package com.afkl.cases.df.service;

import com.afkl.cases.df.common.exception.HttpException;
import com.afkl.cases.df.common.http.PoolableHttpJsonClient;
import com.afkl.cases.df.model.AirportModel;
import com.afkl.cases.df.common.http.model.JsonRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pvaughan on 02/12/2016.
 */
@Service
public class AirportsServiceImpl implements AirportsService {

    @Value("${travel-api.base-url}")
    private String BASE_URL;

    @Value("${travel-api.airport}")
    private String AIRPORT_URL;


    private final PoolableHttpJsonClient httpJsonClient;

    @Autowired
    public AirportsServiceImpl(PoolableHttpJsonClient poolableHttpClient) {
        this.httpJsonClient = poolableHttpClient;
    }


    @Override
    public AirportModel getAirPort(){

        Map<String, String> params = new HashMap<String, String>();

        try {
            return httpJsonClient.doGet(
                    JsonRequest.newBuilder()
                            .url(this.getServerBaseAddress() + String.format(AIRPORT_URL,"AMS"))
                            .headers(getHeaders())
                            .params(params)
                            .build(),
                    new TypeReference<AirportModel>() {
                    }
            );
        } catch (HttpException e) {
            throw new RuntimeException(e);
        }
    }

    private String getServerBaseAddress(){
        return BASE_URL;
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "bearer 1a53f8a2-31f0-4292-8491-7cc8810ab65e");
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
