package com.afkl.cases.df.endpoints;

import com.afkl.cases.df.model.AirportModel;
import com.afkl.cases.df.model.AuthToken;
import com.afkl.cases.df.service.AirportsService;
import com.afkl.cases.df.service.AuthService;
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
    private final AuthService authService;

    @Autowired
    public AirportsController(AirportsService airportsService, AuthService authService) {
        this.airportsService = airportsService;
        this.authService = authService;
    }

    @RequestMapping("/greeting")
    public AirportModel greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return this.airportsService.getAirPort();
    }

    @RequestMapping("/auth")
    public AuthToken greeting() {
        return this.authService.getAuthToken();
    }
}