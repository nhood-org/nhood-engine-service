package com.h8.nh.service.port.webflux;

import com.h8.nh.service.app.AppAPI;
import com.h8.nh.service.app.command.AddDataCommand;
import com.h8.nh.service.app.command.AddDataCommandHandlerException;
import com.h8.nh.service.dto.EngineDataDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class AddDataRequestHandlerWebFluxAdapter implements AddDataRequestHandler {

    private final AppAPI app;

    public AddDataRequestHandlerWebFluxAdapter(AppAPI app) {
        this.app = app;
    }

    public Mono<UUID> add(EngineDataDTO data)
            throws WebFluxAPIException {
        var cmd = AddDataCommand.of(data.getBigDecimalKey(), data.getReference());
        try {
            var response = app.handle(cmd);
            return Mono.just(response.getUuid());
        } catch (AddDataCommandHandlerException e) {
            throw new WebFluxAPIException("Could not execute application add-data command", e);
        }
    }
}
