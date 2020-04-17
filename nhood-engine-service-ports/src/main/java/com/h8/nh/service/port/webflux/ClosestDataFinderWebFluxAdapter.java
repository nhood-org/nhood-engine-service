package com.h8.nh.service.port.webflux;

import com.h8.nh.service.app.command.FindClosestDataCommand;
import com.h8.nh.service.app.command.FindClosestDataCommandHandler;
import reactor.core.publisher.Mono;

public class ClosestDataFinderWebFluxAdapter implements ClosestDataFinder {

    private FindClosestDataCommandHandler finder = new FindClosestDataCommandHandler();

    @Override
    public Mono<ClosestDataDTO> findByID(int id) {
        var cmd = new FindClosestDataCommand(id);
        var result = finder.Handle(cmd);
        return Mono.just(new ClosestDataDTO(result.getId()));
    }
}
