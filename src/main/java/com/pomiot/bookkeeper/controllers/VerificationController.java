package com.pomiot.bookkeeper.controllers;

import com.pomiot.bookkeeper.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class VerificationController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/verify")
    public String verifyUserEmail(@RequestParam("token") String token){

        userService.verifyUserEmail(token);
        return "redirect:/login";
    }

}
