package com.h8.nh.service.port.webflux;

public class ClosestDataFinderException extends Exception {

    public ClosestDataFinderException(String message) {
        super("ClosestDataFinderException: " + message);
    }
}
