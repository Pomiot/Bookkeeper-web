package com.pomiot.bookkeeper.controllers;

import com.pomiot.bookkeeper.model.User;
import com.pomiot.bookkeeper.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User getUser(Principal principal){
        return userService.getUserByUsername(principal.getName());
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public User createUser(User user){
        return userService.createUser(user);
    }
}
