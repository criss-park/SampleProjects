package com.example.log.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BatchService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${batch.sleep.interval}")
    private int userBucketPath;

    @PostConstruct
    public void init(){

        while(true){

            double dValue = Math.random();
            int iValue = (int)(dValue * 10);


            logger.info("{ \"NEXCORE\":" + iValue + " }");

            try {
                Thread.sleep(userBucketPath);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
