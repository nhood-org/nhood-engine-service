package com.h8.nh.service.port.webflux;

import com.h8.nh.service.app.command.FindClosestDataCommand;
import com.h8.nh.service.app.command.FindClosestDataCommandHandler;
import reactor.core.publisher.Mono;

public class ClosestDataFinderWebFluxAdapter implements ClosestDataFinder {

    private FindClosestDataCommandHandler finder = new FindClosestDataCommandHandler();

    @Override
    public Mono<ClosestData> findByID(int id) {
        var cmd = new FindClosestDataCommand(id);
        var result = finder.handle(cmd);
        return Mono.just(new ClosestData(result.getId()));
    }
}
