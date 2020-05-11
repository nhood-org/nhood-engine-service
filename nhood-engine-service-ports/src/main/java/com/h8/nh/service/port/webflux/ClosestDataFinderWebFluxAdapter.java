package com.h8.nh.service.port.webflux;

import com.h8.nh.service.app.query.FindClosestDataQuery;
import com.h8.nh.service.app.query.FindClosestDataQueryHandler;
import reactor.core.publisher.Mono;

public class ClosestDataFinderWebFluxAdapter implements ClosestDataFinder {

    private final FindClosestDataQueryHandler finder = new FindClosestDataQueryHandler();

    @Override
    public Mono<ClosestData> findByID(int id) {
        var cmd = new FindClosestDataQuery(id);
        var result = finder.handle(cmd);
        return Mono.just(new ClosestData(result.getId()));
    }
}
