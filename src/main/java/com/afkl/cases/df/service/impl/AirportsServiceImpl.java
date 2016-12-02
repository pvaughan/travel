package com.afkl.cases.df.service.impl;

import com.afkl.cases.df.common.exception.HttpException;
import com.afkl.cases.df.common.http.PoolableHttpJsonClient;
import com.afkl.cases.df.model.AirportModel;
import com.afkl.cases.df.common.http.model.JsonRequest;
import com.afkl.cases.df.service.AbstractDestinationService;
import com.afkl.cases.df.service.AirportsService;
import com.afkl.cases.df.service.AuthService;
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
public class AirportsServiceImpl extends AbstractDestinationService implements AirportsService {

    @Value("${travel-api.airport}")
    private String AIRPORT_URL;

    @Autowired
    public AirportsServiceImpl(PoolableHttpJsonClient poolableHttpClient, AuthService authService) {
        super(poolableHttpClient, authService);
    }

    @Override
    public AirportModel getAirPort(){

        Map<String, String> params = new HashMap<String, String>();

        try {
            return this.getHttpJsonClient().doGet(
                    JsonRequest.newBuilder()
                            .url(this.getServerBaseAddress() + String.format(AIRPORT_URL,"AMS"))
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

}
