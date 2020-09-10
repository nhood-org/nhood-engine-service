package com.h8.nh.service.port.webflux;

import com.h8.nh.service.app.AppAPI;
import com.h8.nh.service.app.command.AddDataCommand;
import com.h8.nh.service.app.command.AddDataCommandHandlerException;
import reactor.core.publisher.Mono;

public class AddDataRequestHandlerWebFluxAdapter implements AddDataRequestHandler {

    private final AppAPI app;

    public AddDataRequestHandlerWebFluxAdapter(AppAPI app) {
        this.app = app;
    }

    public Mono<EngineDataDTO> add(EngineDataDTO data)
            throws WebFluxAPIException {
        var cmd = AddDataCommand.of(data.getId(), data.getBigDecimalKey());
        try {
            app.handle(cmd);
            return Mono.just(data);
        } catch (AddDataCommandHandlerException e) {
            throw new WebFluxAPIException("Could not execute application add-data command", e);
        }
    }
}
