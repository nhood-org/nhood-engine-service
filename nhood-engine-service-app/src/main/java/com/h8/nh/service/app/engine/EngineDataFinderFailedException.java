package com.h8.nh.service.app.engine;

public class EngineDataFinderFailedException extends Exception {
    public EngineDataFinderFailedException(String message) {
        super(message);
    }
    public EngineDataFinderFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
