package com.beaconfire.HRServer.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

    @GetMapping("/test")
    public String test(){
        return "testing";
    }
}
