package com.example.log.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BatchService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init(){

        while(true){

            double dValue = Math.random();
            int iValue = (int)(dValue * 10);


            logger.info("{ \"NEXCORE\":" + iValue + " }");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
