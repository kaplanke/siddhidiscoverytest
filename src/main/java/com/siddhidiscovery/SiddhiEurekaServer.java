package com.siddhidiscovery;

import io.siddhi.core.SiddhiManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import javax.annotation.PostConstruct;


@EnableEurekaServer
@SpringBootApplication
public class SiddhiEurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(SiddhiEurekaServer.class, args);
    }

}
