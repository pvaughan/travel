package com.afkl.cases.df.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pvaughan on 04/12/2016.
 */
@Controller
public class HomeController {


    @RequestMapping({"/login", "/flights/**"})
    public String index() {
        return "/index.html";
    }
}
