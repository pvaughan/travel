package com.afkl.cases.df.service;

import com.afkl.cases.df.model.StatisticModel;
import com.afkl.cases.df.model.StatisticReport;

import java.util.List;

/**
 * Created by pvaughan on 06/12/2016.
 */
public interface StatisticService {

    void addNewStatistic(StatisticModel statisticModel);

    List<StatisticModel> getStatisticModels();

    StatisticReport getStatisticReport();
}
