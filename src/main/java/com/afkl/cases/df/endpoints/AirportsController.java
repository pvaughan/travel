package com.afkl.cases.df.endpoints;

import com.afkl.cases.df.model.AirportModel;
import com.afkl.cases.df.model.AirportsResult;
import com.afkl.cases.df.model.AuthToken;
import com.afkl.cases.df.service.AirportsService;
import com.afkl.cases.df.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private final AuthService authService;

    @Autowired
    public AirportsController(AirportsService airportsService, AuthService authService) {
        this.airportsService = airportsService;
        this.authService = authService;
    }

    @RequestMapping("/airports")
    public AirportsResult getAirports() {
        return this.airportsService.getAirPorts();
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