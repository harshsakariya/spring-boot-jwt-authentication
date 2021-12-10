package com.harsh.jwtdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/welcome")
    public String welcomePage(){
        return "this is private page";
    }

}
