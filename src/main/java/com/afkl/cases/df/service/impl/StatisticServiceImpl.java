package com.afkl.cases.df.service.impl;

import com.afkl.cases.df.model.StatisticModel;
import com.afkl.cases.df.model.StatisticReport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by pvaughan on 06/12/2016.
 */
@Service
public class StatisticServiceImpl implements com.afkl.cases.df.service.StatisticService {


    private long avgResponseTime;
    private long minResponseTime;
    private long maxResponseTime;

    private List<StatisticModel> statisticModels = new ArrayList<>();

    @Override
    public void addNewStatistic(StatisticModel statisticModel){
        this.statisticModels.add(statisticModel);
    }

    @Override
    public List<StatisticModel> getStatisticModels() {
        return statisticModels;
    }

    @Override
    public StatisticReport getStatisticReport(){
        this.calculate();
        final StatisticReport statisticReport = new StatisticReport();
        statisticReport.setAvgResponseTime(this.avgResponseTime);
        statisticReport.setMaxResponseTime(this.maxResponseTime);
        statisticReport.setMinResponseTime(this.minResponseTime);
        for (StatisticModel statisticModel : statisticModels) {
            switch (statisticModel.getResponseCode()){
                case 200:
                    statisticReport.getRespoonse200().add(statisticModel);
                    break;
                case 404:
                    statisticReport.getRespoonse404().add(statisticModel);
                    break;
                case 500:
                    statisticReport.getRespoonse500().add(statisticModel);
                    break;
            }
        }
        return statisticReport;
    }


    private void calculate(){
        this.minResponseTime = Collections.max(statisticModels, new compRequestCompletionTime()).getRequestCompletionTime();
        this.maxResponseTime = Collections.min(statisticModels, new compRequestCompletionTime()).getRequestCompletionTime();
        this.avgResponseTime = this.calculateAverage(this.statisticModels);
    }


    private long calculateAverage(List <StatisticModel> statisticModels) {
        long sum = 0L;
        if(!statisticModels.isEmpty()) {
            for (StatisticModel mark : statisticModels) {
                sum += mark.getRequestCompletionTime();
            }
            return sum / statisticModels.size();
        }
        return sum;
    }

    public class compRequestCompletionTime implements Comparator<StatisticModel> {
        public int compare(StatisticModel a, StatisticModel b) {
            if (a.getRequestCompletionTime() > b.getRequestCompletionTime())
                return -1; // highest value first
            if (a.getRequestCompletionTime() == b.getRequestCompletionTime())
                return 0;
            return 1;
        }
    }

}

