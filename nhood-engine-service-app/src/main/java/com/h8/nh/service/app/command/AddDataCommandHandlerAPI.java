package com.h8.nh.service.app.command;

public interface AddDataCommandHandlerAPI {
    AddDataCommandResult handle(AddDataCommand command)
            throws AddDataCommandHandlerException;
}
