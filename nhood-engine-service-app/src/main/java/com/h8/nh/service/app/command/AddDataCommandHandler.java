package com.h8.nh.service.app.command;

import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataRepository;
import com.h8.nh.service.app.engine.EngineDataRepositoryException;

public class AddDataCommandHandler implements AddDataCommandHandlerAPI {

    private final EngineDataRepository repository;

    public AddDataCommandHandler(EngineDataRepository repository) {
        this.repository = repository;
    }

    public AddDataCommandResult handle(AddDataCommand command)
            throws AddDataCommandHandlerException {
        EngineData data = EngineData.of(command.getId(), command.getKey());

        try {
            repository.add(data);
        } catch (EngineDataRepositoryException e) {
            throw new AddDataCommandHandlerException("could not add data to engine repository", e);
        }

        return new AddDataCommandResult();
    }
}
