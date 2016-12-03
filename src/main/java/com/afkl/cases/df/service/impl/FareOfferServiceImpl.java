package com.afkl.cases.df.service.impl;

import com.afkl.cases.df.common.exception.HttpException;
import com.afkl.cases.df.common.http.PoolableHttpJsonClient;
import com.afkl.cases.df.common.http.model.JsonRequest;
import com.afkl.cases.df.model.AirportModel;
import com.afkl.cases.df.model.FareOffer;
import com.afkl.cases.df.service.AbstractDestinationService;
import com.afkl.cases.df.service.AuthService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pvaughan on 03/12/2016.
 */
@Service
public class FareOfferServiceImpl extends AbstractDestinationService implements com.afkl.cases.df.service.FareOfferService {

    @Value("${travel-api.fareOffers}")
    private String FARE_OFFERS_URL;

    @Autowired
    public FareOfferServiceImpl(PoolableHttpJsonClient poolableHttpClient, AuthService authService) {
        super(poolableHttpClient, authService);
    }

    @Override
    public FareOffer getFareOffers(String origenCode, String destinationCode) {
        Map<String, String> params = new HashMap<String, String>();

        try {
            return this.getHttpJsonClient().doGet(
                    JsonRequest.newBuilder()
                            .url(this.getServerBaseAddress() + String.format(FARE_OFFERS_URL,origenCode,destinationCode))
                            .headers(this.getSecurityHeader())
                            .params(params)
                            .build(),
                    new TypeReference<FareOffer>() {
                    }
            );
        } catch (HttpException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFareOffersUrlL() {
        return FARE_OFFERS_URL;
    }

}
