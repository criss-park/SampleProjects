package com.simplify.eurekaclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    private static Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/ping")
    public List<ServiceInstance> ping(){

        List<ServiceInstance> instances = discoveryClient.getInstances("client-service");

        LOGGER.info("INSTNCES: count={}", instances.size());

        instances.stream().forEach(
                it -> LOGGER.info("INSTANCE: id={}, port={}", it.getServiceId(), it.getPort())
        );

        return instances;


    }
}
