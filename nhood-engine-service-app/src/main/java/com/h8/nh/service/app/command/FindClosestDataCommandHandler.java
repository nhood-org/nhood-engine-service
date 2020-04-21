package com.h8.nh.service.app.command;

public class FindClosestDataCommandHandler {

    public FindClosestDataCommandResult handle(FindClosestDataCommand command) {
        var id = command.getId();
        return new FindClosestDataCommandResult(id);
    }
}
