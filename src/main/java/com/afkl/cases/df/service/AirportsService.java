package com.afkl.cases.df.service;

import com.afkl.cases.df.common.http.PoolableHttpJsonClient;
import com.afkl.cases.df.model.AirportModel;
import com.afkl.cases.df.model.AuthToken;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pvaughan on 02/12/2016.
 */
public interface AirportsService {
    AirportModel getAirPort();


}
