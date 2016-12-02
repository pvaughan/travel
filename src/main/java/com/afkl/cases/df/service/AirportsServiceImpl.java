package com.afkl.cases.df.service;

import org.springframework.stereotype.Service;

/**
 * Created by pvaughan on 02/12/2016.
 */
@Service
public class AirportsServiceImpl implements AirportsService {

    @Override
    public String getAirPort(){
        return "AMS";
    }
}
