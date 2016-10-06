package com.pomiot.bookkeeper.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String getHome(Authentication authentication){
        return "index";
    }
}
