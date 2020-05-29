package com.h8.nh.service.port.webflux;

import com.h8.nh.nhoodengine.core.DataResource;
import com.h8.nh.nhoodengine.matrix.DataMatrixRepositoryFailedException;
import com.h8.nh.service.app.engine.*;
import com.h8.nh.service.app.query.FindClosestDataQuery;
import com.h8.nh.service.app.query.FindClosesDataQueryHandler;
import reactor.core.publisher.Mono;

public class ClosestDataFinderWebFluxAdapter implements ClosestDataFinder {
    public static final int RESULT_SIZE = 5;

    private final EngineDataRepository repository = new EngineDataRepositoryImpl(EngineDataResourceKey.METADATA_SIZE);
    private final EngineDataFinder finder = new EngineDataFinderImpl(repository);
    private final FindClosesDataQueryHandler handler = new FindClosesDataQueryHandler(finder);

    public ClosestDataFinderWebFluxAdapter() {
        try {
            for (int i = 0; i < 100; i++) {
                repository.add(DataResource.<EngineDataResourceKey, EngineData>builder()
                        .key(new EngineDataResourceKey(i))
                        .data(new EngineData(i))
                        .build());
            }
        } catch (DataMatrixRepositoryFailedException e) {
                System.out.println(e);
        }
    }

    @Override
    public Mono<ClosestData> findByID(int id) {
        var query = new FindClosestDataQuery(id, RESULT_SIZE);
        var result = handler.handle(query);
        return Mono.just(new ClosestData(result.getResults()));
    }
}
