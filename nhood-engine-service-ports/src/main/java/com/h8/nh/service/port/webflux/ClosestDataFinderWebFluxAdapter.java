package com.h8.nh.service.port.webflux;

import com.h8.nh.service.app.query.NoopFindClosestDataQuery;
import com.h8.nh.service.app.query.NoopFindClosestDataQueryHandler;
import reactor.core.publisher.Mono;

public class ClosestDataFinderWebFluxAdapter implements ClosestDataFinder {

    private final NoopFindClosestDataQueryHandler finder = new NoopFindClosestDataQueryHandler();

    @Override
    public Mono<ClosestData> findByID(int id) {
        var query = new NoopFindClosestDataQuery(id);
        var result = finder.handle(query);
        return Mono.just(new ClosestData(result.getId()));
    }
}
