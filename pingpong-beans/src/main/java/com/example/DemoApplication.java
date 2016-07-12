package com.example;

import com.example.common.PingState;
import com.example.common.Ping;
import com.example.common.impl.PingBean;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * A sample Spring Boot application that starts the Camel routes.
 */
@SpringBootApplication
public class DemoApplication {

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private HealthEndpoint health;

    @Bean
    public Ping oneBean() {
        return new PingBean(PingState.PING);
    }

    @Bean
    public Ping anotherBean() {
        return new PingBean(PingState.PONG);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
