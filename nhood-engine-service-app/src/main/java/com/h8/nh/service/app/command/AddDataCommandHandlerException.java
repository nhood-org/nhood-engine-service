package com.h8.nh.service.app.command;

public class AddDataCommandHandlerException extends Exception {
    public AddDataCommandHandlerException(String message) {
        super(message);
    }
    public AddDataCommandHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
