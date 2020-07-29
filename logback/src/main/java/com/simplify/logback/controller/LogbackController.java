package com.simplify.logback.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@Controller
public class LogbackController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping( value="/" )
    public @ResponseBody String test(){
        logger.info("info log");

        for(Map.Entry<String, String> entry : System.getenv().entrySet()){
            logger.info("[ENV] " + entry.getKey() + " : " + entry.getValue());
        }

        try {
            logger.info(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "TEST";
    }
}
