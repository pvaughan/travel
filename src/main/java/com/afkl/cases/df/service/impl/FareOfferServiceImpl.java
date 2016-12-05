package com.afkl.cases.df.service.impl;

import com.afkl.cases.df.common.exception.HttpException;
import com.afkl.cases.df.common.http.PoolableHttpJsonClient;
import com.afkl.cases.df.common.http.model.JsonRequest;
import com.afkl.cases.df.model.AirportModel;
import com.afkl.cases.df.model.FareOffer;
import com.afkl.cases.df.service.AbstractDestinationService;
import com.afkl.cases.df.service.AirportsService;
import com.afkl.cases.df.service.AuthService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by pvaughan on 03/12/2016.
 */
@Service
public class FareOfferServiceImpl extends AbstractDestinationService implements com.afkl.cases.df.service.FareOfferService {

    private final AirportsService airportsService;

    @Value("${travel-api.fareOffers}")
    private String FARE_OFFERS_URL;

    @Autowired
    public FareOfferServiceImpl(PoolableHttpJsonClient poolableHttpClient, AuthService authService, AirportsService airportsService) {
        super(poolableHttpClient, authService);
        this.airportsService = airportsService;
    }

    @Override
    public FareOffer getFareOffers(String origenCode, String destinationCode) throws InterruptedException, ExecutionException {

        Executor executor = Executors.newFixedThreadPool(3);
        CompletionService<FareOffer> completionService = new ExecutorCompletionService<>(executor);
        CompletionService<AirportModel> airportsCompletionService = new ExecutorCompletionService<>(executor);

        submitFareOfferExecuter(origenCode, destinationCode, completionService);
        submitLocation(origenCode, airportsCompletionService);
        submitLocation(destinationCode, airportsCompletionService);

        Future<FareOffer> resultFuture = completionService.take();
        FareOffer result = resultFuture.get();

        int received = 0;
        boolean errors = false;

        AirportModel destination = null;
        AirportModel origen = null;

        while(received < 2 && !errors) {
            Future<AirportModel> airportModelFuture = airportsCompletionService.take();
            try {
                final AirportModel airportModel = airportModelFuture.get();
                if(airportModel.getCode().equals(destinationCode)){
                    destination = airportModel;
                }else{
                    origen = airportModel;
                }
                received ++;
            }
            catch(Exception e) {

                errors = true;
            }
        }
        result.setDestinationInfo(destination);
        result.setOriginInfo(origen);
        return  result;

    }

    private void submitFareOfferExecuter(String origenCode, String destinationCode, CompletionService<FareOffer> completionService) {
        completionService.submit(() -> {

            Map<String, String> params = new HashMap<>();

            try {
                return getHttpJsonClient().doGet(
                        JsonRequest.newBuilder()
                                .url(getServerBaseAddress() + String.format(getFareOffersUrl(), origenCode, destinationCode))
                                .headers(getSecurityHeader())
                                .params(params)
                                .build(),
                        new TypeReference<FareOffer>() {
                        }
                );
            } catch (HttpException e) {
                throw new RuntimeException(e);
            }

        });
    }

    private void submitLocation(String origenCode, CompletionService<AirportModel> airportsCompletionService) {
        airportsCompletionService.submit(() -> {
            try {
                return airportsService.getAirPort(origenCode);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    protected String getFareOffersUrl() {
        return FARE_OFFERS_URL;
    }

}
