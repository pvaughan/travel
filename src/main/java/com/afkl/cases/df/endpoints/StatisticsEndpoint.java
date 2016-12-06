package com.afkl.cases.df.endpoints;

import com.afkl.cases.df.model.StatisticModel;
import com.afkl.cases.df.model.StatisticReport;
import com.afkl.cases.df.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pvaughan on 06/12/2016.
 */
@RestController
public class StatisticsEndpoint {

    @Autowired
    private StatisticService statisticService;

    @RequestMapping("/getStatistics")
    public List<StatisticModel> getStatistics(){
        return statisticService.getStatisticModels();
    }

    @RequestMapping("/getStatisticsReport")
    public StatisticReport getStatisticsReport(){
        return statisticService.getStatisticReport();
    }
}
