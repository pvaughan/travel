package com.afkl.cases.df.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pvaughan on 03/12/2016.
 */
@JsonAutoDetect
public class AirportsResultItems {

    List<AirportModel> locations = new ArrayList<>();

    public List<AirportModel> getLocations() {
        return locations;
    }

    public void setLocations(List<AirportModel> locations) {
        this.locations = locations;
    }
}
