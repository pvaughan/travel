package com.afkl.cases.df.endpoints;

import com.afkl.cases.df.model.AirportModel;
import com.afkl.cases.df.model.FareOffer;
import com.afkl.cases.df.service.FareOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * Created by pvaughan on 03/12/2016.
 */
@RestController
public class AirFareOfferEndPoint {

    private final FareOfferService fareOfferService;

    @Autowired
    public AirFareOfferEndPoint(FareOfferService fareOfferService) {
        this.fareOfferService = fareOfferService;
    }

    @RequestMapping("/fareOffer/{origenCode}/{destinationCode}")
    public FareOffer greeting(@PathVariable("origenCode") String origenCode, @PathVariable("destinationCode") String destinationCode) throws ExecutionException, InterruptedException {
        return this.fareOfferService.getFareOffers(origenCode,destinationCode);
    }

}
