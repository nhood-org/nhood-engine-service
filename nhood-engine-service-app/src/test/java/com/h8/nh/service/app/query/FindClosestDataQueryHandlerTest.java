package com.h8.nh.service.app.query;

import com.h8.nh.nhoodengine.core.DataFinderFailedException;
import com.h8.nh.nhoodengine.core.DataResource;
import com.h8.nh.nhoodengine.matrix.DataMatrixRepositoryFailedException;
import com.h8.nh.service.app.engine.*;
import com.h8.nh.nhoodengine.core.impl.DataScoreComputationEngine;

import org.junit.jupiter.api.Test;

public class FindClosestDataQueryHandlerTest {

    @Test
    void shouldReturnClosestPoints() throws DataMatrixRepositoryFailedException, DataFinderFailedException {
        EngineDataRepository repository =
            new EngineDataRepositoryImpl(EngineDataResourceKey.METADATA_SIZE);
        for(int i = 0; i < 100; i++) {
            repository.add(DataResource.<EngineDataResourceKey, EngineData>builder()
                    .key(new EngineDataResourceKey(i))
                    .data(new EngineData(i))
                    .build());
        }

        var handler = new FindClosesDataQueryHandler(new EngineDataFinderImpl(new DataScoreComputationEngine(repository)));
        var query = new FindClosestDataQuery(10);
        var result = handler.handle(query);
        System.out.println(result);

    }
}
