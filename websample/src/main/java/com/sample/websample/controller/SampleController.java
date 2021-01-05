package com.sample.websample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class SampleController {

    @GetMapping("/")
    public String sample(){

        String info = "";

        try{
            info = InetAddress.getLocalHost().getHostName();
            info += "(" + InetAddress.getLocalHost().getHostAddress() + ")";
        } catch( UnknownHostException e ){
            e.printStackTrace();
        }

        return info;
    }
}
