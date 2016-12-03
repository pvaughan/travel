package com.afkl.cases.df.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by pvaughan on 03/12/2016.
 */
@JsonAutoDetect
public class AirportsResult {

    private AirportsResultItems _embedded;
    private AirportsResultPage page;

    public AirportsResultItems get_embedded() {
        return _embedded;
    }

    public void set_embedded(AirportsResultItems _embedded) {
        this._embedded = _embedded;
    }

    public AirportsResultPage getPage() {
        return page;
    }

    public void setPage(AirportsResultPage page) {
        this.page = page;
    }
}
