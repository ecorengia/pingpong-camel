package com.example.common.impl;

import com.example.common.PingState;
import com.example.common.Ping;

public class PingBean implements Ping {

    private PingState state;

    public PingBean() {
    }

    public PingBean(PingState state) {
        this.state = state;
    }

    @Override
    public PingState ping() {
        return state;
    }

    public PingState getState() {
        return state;
    }

    public void setState(PingState state) {
        this.state = state;
    }
}
