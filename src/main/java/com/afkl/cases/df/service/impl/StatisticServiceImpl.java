package com.afkl.cases.df.service.impl;

import com.afkl.cases.df.model.StatisticModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pvaughan on 06/12/2016.
 */
@Service
public class StatisticServiceImpl implements com.afkl.cases.df.service.StatisticService {

    private List<StatisticModel> statisticModels = new ArrayList<>();

    @Override
    public void addNewStatistic(StatisticModel statisticModel){
        this.statisticModels.add(statisticModel);
    }

    @Override
    public List<StatisticModel> getStatisticModels() {
        return statisticModels;
    }
}
