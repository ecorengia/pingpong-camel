package com.example.common;

public enum PingState {
    PING("PING"),
    PONG("PONG"),
    OUT("OUT");

    // A unique identifier for demo state
    private final String demoState;

    PingState(String demoState) {
        this.demoState = demoState;
    }

    public String getDemoState() {
        return demoState;
    }

}
