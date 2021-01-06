package com.sample.websample.config;

import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class PrometheusConfig {

    private Gauge sampleGauge;

    private Gauge totalGauge;
    private Gauge okGauge;
    private Gauge errGauge;

    @PostConstruct
    public void init(){

        System.out.println("init()");

        // To use JVM Metrics,
        // Implementation 'io.prometheus:simpleclient_hotspot:0.6.0' add to gradle
        DefaultExports.initialize(); // Export JVM Metrics into http endpoint

        sampleGauge = totalGauge.build("njf_sample", "sample metrics").register();

        totalGauge = totalGauge.build("njf_tx_total", "sample metrics").register();
        okGauge    = totalGauge.build("njf_tx_ok", "sample metrics").register();
        errGauge   = totalGauge.build("njf_tx_err", "sample metrics").register();

    }

    public Gauge getSampleGauge(){
        return sampleGauge;
    }

    public Gauge getTotalGauge() {
        return totalGauge;
    }

    public Gauge getOkGauge() {
        return okGauge;
    }

    public Gauge getErrGauge() {
        return errGauge;
    }

    @Bean
    public HTTPServer prometheusServer() throws IOException {
        System.out.println("prometheusServer() port: 9111");
        return new HTTPServer(9111);
    }
}
