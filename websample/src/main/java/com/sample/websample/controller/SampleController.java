package com.sample.websample.controller;

import com.sample.websample.config.PrometheusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class SampleController {

    @Autowired
    PrometheusConfig prometheusConfig;

    @GetMapping("/")
    public String sample(){

        String info = "";

        try{
            info = InetAddress.getLocalHost().getHostName();
            info += "(" + InetAddress.getLocalHost().getHostAddress() + ")";
        } catch( UnknownHostException e ){
            e.printStackTrace();
        }

        setTxValue();

        return info;
    }

    private void setTxValue() {
        double randVal = Math.random();

        prometheusConfig.getSampleGauge().set성(randVal);

        // 요청이 들어올 때, 임의로 에러 카운트 생
        if((int)(randVal * 10) % 5 == 0 ){
            prometheusConfig.getErrGauge().inc();
        }else{
            prometheusConfig.getOkGauge().inc();
        }

        prometheusConfig.getTotalGauge().inc();

    }
}
