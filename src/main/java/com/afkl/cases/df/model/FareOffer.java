package com.afkl.cases.df.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;

/**
 * Created by pvaughan on 03/12/2016.
 */
@JsonAutoDetect
public class FareOffer {

    private BigDecimal amount;
    private String currency;
    private String origin;
    private String destination;


    private AirportModel originInfo;
    private AirportModel destinationInfo;


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public AirportModel getOriginInfo() {
        return originInfo;
    }

    public void setOriginInfo(AirportModel originInfo) {
        this.originInfo = originInfo;
    }

    public AirportModel getDestinationInfo() {
        return destinationInfo;
    }

    public void setDestinationInfo(AirportModel destinationInfo) {
        this.destinationInfo = destinationInfo;
    }
}
