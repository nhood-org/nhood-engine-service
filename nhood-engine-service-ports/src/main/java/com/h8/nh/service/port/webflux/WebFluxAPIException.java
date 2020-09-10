package com.h8.nh.service.port.webflux;

public class WebFluxAPIException extends Exception {
    public WebFluxAPIException(String message) {
        super(message);
    }

    public WebFluxAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
