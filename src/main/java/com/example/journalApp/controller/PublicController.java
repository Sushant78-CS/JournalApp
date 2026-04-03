package com.example.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journalApp.entity.UserEntry;
import com.example.journalApp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

    @PostMapping("/create-user")
    public boolean createUserSer(@RequestBody UserEntry userEntry) {
        try {
            userService.createNewUser(userEntry);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}