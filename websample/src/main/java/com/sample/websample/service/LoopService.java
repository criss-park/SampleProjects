package com.sample.websample.service;

import com.sample.websample.config.PrometheusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LoopService {

    @Autowired
    PrometheusConfig prometheusConfig;

    @Scheduled( fixedRate = 3000 )
    public void checkRecords() {

        double randVal = Math.random();

        prometheusConfig.getSampleGauge().set(randVal);

//        if((int)(randVal * 10) % 5 == 0 ){
//            prometheusConfig.getErrGauge().inc();
//        }else{
//            prometheusConfig.getOkGauge().inc();
//        }
//        prometheusConfig.getTotalGauge().inc();

        //prometheusConfig.getSampleGauge().inc();
        //System.out.println("sampleGauge=" + prometheusConfig.getSampleGauge().get());
    }
}
