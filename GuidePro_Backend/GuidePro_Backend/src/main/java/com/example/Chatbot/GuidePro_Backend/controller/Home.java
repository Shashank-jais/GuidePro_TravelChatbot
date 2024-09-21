package com.example.Chatbot.GuidePro_Backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Home {

    @RequestMapping("/")
    public String hello(){
        return "Hello World Mother ";
    }
}
