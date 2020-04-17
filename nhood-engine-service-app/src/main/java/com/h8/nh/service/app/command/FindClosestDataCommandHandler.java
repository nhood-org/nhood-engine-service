package com.h8.nh.service.app.command;

public class FindClosestDataCommandHandler {

    public FindClosestDataCommandResult Handle(FindClosestDataCommand command) {
        var id = command.getId();
        return new FindClosestDataCommandResult(id);
    }
}
