package com.example.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.ConnectionFactory;

/**
 * A sample Spring Boot application that starts the Camel routes.
 * NOTE: Server, client and Message Broker are all together on
 * the same bundle for simplicity. It is not recommended.
 */
@EnableJms
@SpringBootApplication
public class DemoApplication {

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private HealthEndpoint health;

    @Bean
    public JmsComponent demoJmsComponent() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(connectionFactory);
        return jmsComponent;

        /**
         * Alternatives:
         * tcp://localhost:{{jms.port}}
         * May I have a pool? (PooledConnectionFactory)
         */
    }

    @Bean
    public DemoProcessor processorBean() {
        return new DemoProcessor();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
