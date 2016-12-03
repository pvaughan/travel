package com.afkl.cases.df.endpoints;

import com.afkl.cases.df.model.AirportModel;
import com.afkl.cases.df.model.AirportsResult;
import com.afkl.cases.df.model.AuthToken;
import com.afkl.cases.df.service.AirportsService;
import com.afkl.cases.df.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by pvaughan on 02/12/2016.
 */
@RestController
public class AirportsController {

    private final AirportsService airportsService;
    private final AuthService authService;

    @Autowired
    public AirportsController(AirportsService airportsService, AuthService authService) {
        this.airportsService = airportsService;
        this.authService = authService;
    }

    @RequestMapping("/airports")
    public AirportsResult getAirports(@RequestParam(value = "page", defaultValue = "1") String page) {
        return this.airportsService.getAirPorts(page);
    }

    @RequestMapping("/airports/search/{q}")
    public AirportsResult searchAirport(@PathVariable("q") String q) {
        return this.airportsService.searchAirport(q);
    }

    @RequestMapping("/airport/{code}")
    public AirportModel greeting(@PathVariable("code") String code) {
        return this.airportsService.getAirPort(code);
    }

    @RequestMapping("/auth")
    public AuthToken greeting() {
        return this.authService.getAuthToken();
    }
}