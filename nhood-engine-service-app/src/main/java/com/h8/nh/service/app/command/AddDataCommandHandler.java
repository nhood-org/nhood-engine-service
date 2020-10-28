package com.h8.nh.service.app.command;

import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataRepository;
import com.h8.nh.service.app.engine.EngineDataRepositoryException;

import java.util.UUID;

public class AddDataCommandHandler implements AddDataCommandHandlerAPI {

    private final EngineDataRepository repository;

    public AddDataCommandHandler(EngineDataRepository repository) {
        this.repository = repository;
    }

    public AddDataCommandResult handle(AddDataCommand command)
            throws AddDataCommandHandlerException {
        EngineData data = EngineData.of(command.getKey(), command.getData());

        try {
            UUID uuid = repository.add(data);
            return AddDataCommandResult.of(uuid);
        } catch (EngineDataRepositoryException e) {
            throw new AddDataCommandHandlerException("Could not add data to engine repository", e);
        }
    }
}
