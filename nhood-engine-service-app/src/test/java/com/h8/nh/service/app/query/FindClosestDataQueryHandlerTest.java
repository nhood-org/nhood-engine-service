package com.h8.nh.service.app.query;

import com.h8.nh.nhoodengine.core.DataFinderFailedException;
import com.h8.nh.nhoodengine.core.DataResource;
import com.h8.nh.nhoodengine.matrix.DataMatrixRepositoryFailedException;
import com.h8.nh.service.app.engine.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FindClosestDataQueryHandlerTest {

    @Test
    void shouldReturnClosestPoints() throws DataMatrixRepositoryFailedException, DataFinderFailedException {
        var repository = new EngineDataRepositoryImpl(EngineDataResourceKey.METADATA_SIZE);
        for(int i = 0; i < 100; i++) {
            repository.add(DataResource.<EngineDataResourceKey, EngineData>builder()
                    .key(new EngineDataResourceKey(i))
                    .data(new EngineData(i))
                    .build());
        }

        var queryMetadata = 10;
        var queryResultSize = 5;
        var handler = new FindClosesDataQueryHandler(new EngineDataFinderImpl(repository));
        var queryResult = handler.handle(new FindClosestDataQuery(queryMetadata, queryResultSize));

        var neighbours = queryResult.getResults();
        Arrays.sort(neighbours);
        assertThat(neighbours).containsExactly(8,9,10,11,12);
    }
}
