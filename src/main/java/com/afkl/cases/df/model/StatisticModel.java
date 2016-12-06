package com.afkl.cases.df.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Date;

/**
 * Created by pvaughan on 06/12/2016.
 */
@JsonAutoDetect
public class StatisticModel {

    private final Date timeStamp;
    private final Long requestCompletionTime;
    private final Integer responseCode;

    public StatisticModel(Date timeStamp, Long requestCompletionTime, Integer responseCode) {
        this.timeStamp = timeStamp;
        this.requestCompletionTime = requestCompletionTime;
        this.responseCode = responseCode;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public Long getRequestCompletionTime() {
        return requestCompletionTime;
    }

    public Integer getResponseCode() {
        return responseCode;
    }
}
