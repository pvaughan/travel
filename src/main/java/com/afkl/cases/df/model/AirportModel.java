package com.afkl.cases.df.model;

/**
 * Created by pvaughan on 02/12/2016.
 */
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class AirportModel {

    private String code;
    private String name;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
