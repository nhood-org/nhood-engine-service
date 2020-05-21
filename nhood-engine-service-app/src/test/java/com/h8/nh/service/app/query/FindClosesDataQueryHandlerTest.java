package com.h8.nh.service.app.query;

import com.h8.nh.service.app.engine.EngineDataFinderImpl;
import com.h8.nh.service.app.engine.EngineDataRepositoryImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FindClosesDataQueryHandlerIntegrationTest {

    @Disabled
    @Test
    void shouldFindClosesNumbers() {
        var engineRepository = new EngineDataRepositoryImpl();
        // TODO: initialize repository

        var engine = new EngineDataFinderImpl(engineRepository);

        var queryMetadata = 10;
        var queryResultSize = 5;
        var query = new FindClosestDataQuery(queryMetadata, queryResultSize);

        var handler = new FindClosesDataQueryHandler(engine);
        var result = handler.handle(query);
        assertThat(result).isNotNull();

        var resultSet = result.getResults();
        assertThat(resultSet).hasSize(queryResultSize);
        assertThat(resultSet).containsExactly(8, 9, 10, 11, 12);
    }
}