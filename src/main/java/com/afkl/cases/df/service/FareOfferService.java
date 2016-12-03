package com.afkl.cases.df.service;

import com.afkl.cases.df.model.FareOffer;

/**
 * Created by pvaughan on 03/12/2016.
 */
public interface FareOfferService {
    FareOffer getFareOffers(String origenCode, String destinationCode);
}
