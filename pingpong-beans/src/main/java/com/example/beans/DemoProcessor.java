package com.example.beans;

import com.example.common.PingState;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(DemoProcessor.class);

    private boolean ping = true;

    @Override
    public void process(Exchange exchange) throws Exception {
        String payload = exchange.getIn().getBody(String.class);
        logger.debug("Processing {}...", payload);
        // Do something with the payload and/or exchange here
        if (Math.random() < 0.2) {
            // Give us 1 in 5 chance of throwing it out
            exchange.getIn().setHeader(DemoConstants.DEMO_HEADER, PingState.OUT);
        } else if (ping) {
            exchange.getIn().setHeader(DemoConstants.DEMO_HEADER, PingState.PING);
        } else {
            exchange.getIn().setHeader(DemoConstants.DEMO_HEADER, PingState.PONG);
        }

        ping = !ping;
    }

}
