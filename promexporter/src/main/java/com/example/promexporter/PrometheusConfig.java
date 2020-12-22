package com.example.promexporter;

import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class PrometheusConfig {

    private Gauge sampleGauge;

    @PostConstruct
    public void init(){
        System.out.println("init()");

        // To use JVM Metrics,
        // Implementation 'io.prometheus:simpleclient_hotspot:0.6.0' add to gradle
        //DefaultExports.initialize(); // Export JVM Metrics into http endpoint

        sampleGauge = Gauge.build("sample", "sample metrics").register();

    }

    public Gauge getSampleGauge(){
        return sampleGauge;
    }

    @Bean
    public HTTPServer prometheusServer() throws IOException {
        System.out.println("prometheusServer() port: 9111");
        return new HTTPServer(9111);
    }
}
