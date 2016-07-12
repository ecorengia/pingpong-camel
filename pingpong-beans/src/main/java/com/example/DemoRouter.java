package com.example;

import com.example.common.PingState;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A demo route for playing ping-pong.
 */
@Component
public class DemoRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Intercept out states and stop the current Exchange
        intercept()
                .when(header(DemoConstants.DEMO_HEADER).isEqualTo(PingState.OUT))
                .log(LoggingLevel.INFO, "Out!!!")
                .setBody(constant(PingState.OUT))
                .to("log:demo")
                .stop();

        // Create a test route for playing ping pong between two beans
        from("timer:demoTimer?period={{timer.period}}")
                .routeId(DemoConstants.DEMO_ROUTE)
                .setBody(constant("a dummy body"))
                .process(new DemoProcessor())
                .choice()
                .when(header(DemoConstants.DEMO_HEADER).isEqualTo(PingState.PING))
                .transform(method("oneBean", "ping"))
                .to("log:demo")
                .otherwise()
                .transform(method("anotherBean", "ping"))
                .to("log:demo");
    }

}
