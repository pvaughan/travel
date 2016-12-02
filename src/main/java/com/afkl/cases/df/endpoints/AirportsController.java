package com.afkl.cases.df.endpoints;

import com.afkl.cases.df.model.AirportModel;
import com.afkl.cases.df.service.AirportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by pvaughan on 02/12/2016.
 */
@RestController
public class AirportsController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private final AirportsService airportsService;

    @Autowired
    public AirportsController(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    @RequestMapping("/greeting")
    public AirportModel greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return this.airportsService.getAirPort();
    }
}