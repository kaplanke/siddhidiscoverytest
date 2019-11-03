package com.siddhidiscovery;

import com.fasterxml.jackson.core.JsonFactory;
import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.event.Event;
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.stream.output.StreamCallback;
import io.siddhi.core.util.EventPrinter;
import io.siddhi.core.util.transport.InMemoryBroker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;


@EnableDiscoveryClient
@SpringBootApplication
public class SiddhiEurekaTest {
    public static void main(String[] args) {
        SpringApplication.run(SiddhiEurekaTest.class, args);
    }
}
