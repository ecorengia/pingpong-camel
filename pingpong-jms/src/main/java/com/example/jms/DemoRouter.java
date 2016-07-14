package com.example.jms;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A demo producer for playing ping-pong.
 */
@Component
public class DemoRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Route from a timer to our ping-pong queue
        from("timer:demoTimer?period={{timer.period}}")
                .routeId("ProducerRoute")
                .setBody(constant("a dummy body"))
                .to("activemq:queue:ping-pong");

        // Route from ping-pong queue to our service bean
        from("activemq:queue:ping-pong")
                .routeId("ConsumerRoute")
                .to("bean:processorBean?method=process&cache=false");

        /**
         * Alternatives:
         *
         * as above but with the ean: prefix
         * from("jms:queue:ping-pong").to("bean:pingServer");
         *
         * bean is using explicit bean bindings to lookup the serverBean bean and invoke the ping method
         * from("jms:queue:ping-pong").bean("pingServer", "ping");
         *
         * the same as above but expressed as a URI configuration
         * from("jms:queue:ping-pong").to("bean:pingServer?method=ping");
         *
         * or using activemq component (which is optimized for activemq)
         * from("activemq:ping-pong").to("pingServer");
         */
    }

}
