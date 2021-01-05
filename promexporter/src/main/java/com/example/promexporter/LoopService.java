package com.example.promexporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LoopService {

    @Autowired
    PrometheusConfig prometheusConfig;

    @Scheduled( fixedRate = 3000 )
    public void checkRecords() {
        prometheusConfig.getSampleGauge().set(Math.random());
        prometheusConfig.getSampleGauge().inc();
        System.out.println("sampleGauge=" + prometheusConfig.getSampleGauge().get());
    }
}
