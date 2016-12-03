package com.afkl.cases.df.service;

import com.afkl.cases.df.model.AirportModel;
import com.afkl.cases.df.model.AirportsResult;

/**
 * Created by pvaughan on 02/12/2016.
 */
public interface AirportsService {

    AirportModel getAirPort(String code);

    AirportsResult getAirPorts();
}
