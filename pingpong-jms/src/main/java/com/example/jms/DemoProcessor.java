package com.example.jms;

import com.example.common.PingState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(DemoProcessor.class);

    private boolean ping = true;

    public void process() {
        if (Math.random() < 0.2) {
            // Give us 1 in 5 chance of throwing it out
            LOG.info(String.format("%s!", PingState.OUT));
        } else if (ping) {
            LOG.info(PingState.PING.getDemoState());
        } else {
            LOG.info(PingState.PONG.getDemoState());
        }

        ping = !ping;
    }
}
