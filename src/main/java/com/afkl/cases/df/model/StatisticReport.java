package com.afkl.cases.df.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pvaughan on 06/12/2016.
 */
@JsonAutoDetect
public class StatisticReport {

    private long avgResponseTime;
    private long minResponseTime;
    private long maxResponseTime;

    private List<StatisticModel> Respoonse200 = new ArrayList<>();
    private List<StatisticModel> Respoonse404 = new ArrayList<>();
    private List<StatisticModel> Respoonse500 = new ArrayList<>();

    public long getAvgResponseTime() {
        return avgResponseTime;
    }

    public void setAvgResponseTime(long avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
    }

    public long getMinResponseTime() {
        return minResponseTime;
    }

    public void setMinResponseTime(long minResponseTime) {
        this.minResponseTime = minResponseTime;
    }

    public long getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(long maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public List<StatisticModel> getRespoonse200() {
        return Respoonse200;
    }

    public void setRespoonse200(List<StatisticModel> respoonse200) {
        Respoonse200 = respoonse200;
    }

    public List<StatisticModel> getRespoonse404() {
        return Respoonse404;
    }

    public void setRespoonse404(List<StatisticModel> respoonse404) {
        Respoonse404 = respoonse404;
    }

    public List<StatisticModel> getRespoonse500() {
        return Respoonse500;
    }

    public void setRespoonse500(List<StatisticModel> respoonse500) {
        Respoonse500 = respoonse500;
    }
}
