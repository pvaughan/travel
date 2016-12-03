package com.afkl.cases.df.service.impl;

import com.afkl.cases.df.common.exception.HttpException;
import com.afkl.cases.df.common.http.PoolableHttpJsonClient;
import com.afkl.cases.df.model.AirportModel;
import com.afkl.cases.df.common.http.model.JsonRequest;
import com.afkl.cases.df.model.AirportsResult;
import com.afkl.cases.df.service.AbstractDestinationService;
import com.afkl.cases.df.service.AirportsService;
import com.afkl.cases.df.service.AuthService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pvaughan on 02/12/2016.
 */
@Service
public class AirportsServiceImpl extends AbstractDestinationService implements AirportsService {

    @Value("${travel-api.airport}")
    private String AIRPORT_URL;

    @Value("${travel-api.listAirports}")
    private String AIRPORTS_URL;

    @Autowired
    public AirportsServiceImpl(PoolableHttpJsonClient poolableHttpClient, AuthService authService) {
        super(poolableHttpClient, authService);
    }

    @Override
    public AirportModel getAirPort(String code) {

        Map<String, String> params = new HashMap<String, String>();

        try {
            return this.getHttpJsonClient().doGet(
                    JsonRequest.newBuilder()
                            .url(this.getServerBaseAddress() + String.format(AIRPORT_URL, code))
                            .headers(this.getSecurityHeader())
                            .params(params)
                            .build(),
                    new TypeReference<AirportModel>() {
                    }
            );
        } catch (HttpException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AirportsResult getAirPorts(String page) {

        return retreiveAirports(null, page);
    }

    @Override
    public AirportsResult searchAirport(String q) {
        return retreiveAirports(q, null);
    }

    private AirportsResult retreiveAirports(String term, String page) {
        Map<String, String> params = new HashMap<String, String>();

        if (!StringUtils.isEmpty(term)) {
            params.put("term", term);
        }

        if (!StringUtils.isEmpty(page)) {
            params.put("page", page);
        }

        try {
            return this.getHttpJsonClient().doGet(
                    JsonRequest.newBuilder()
                            .url(this.getServerBaseAddress() + this.getAirportsUrl())
                            .headers(this.getSecurityHeader())
                            .params(params)
                            .build(),
                    new TypeReference<AirportsResult>() {
                    }
            );
        } catch (HttpException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAirportsUrl() {
        return AIRPORTS_URL;
    }
}
