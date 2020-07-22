package com.h8.nh.service.app.query;

import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataFinder;
import com.h8.nh.service.app.engine.EngineDataFinderFailedException;
import com.h8.nh.service.app.engine.EngineDataResourceKey;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindClosestDataQueryHandlerTest {

    @Test
    void shouldReturnClosestPoints()
            throws EngineDataFinderFailedException, FindClosesDataQueryHandlerFailedException {
        var queryKey = new BigDecimal[]{new BigDecimal("0")};
        var queryResultSize = 4;

        var engine = mock(EngineDataFinder.class);
        var engineDataKey = new EngineDataResourceKey(queryKey);
        var engineData = Arrays.asList(
                new EngineData("ID_1"),
                new EngineData("ID_2"),
                new EngineData("ID_3"),
                new EngineData("ID_4")
        );
        when(engine.find(engineDataKey, queryResultSize))
                .thenReturn(engineData);

        var handler = new FindClosesDataQueryHandler(engine);

        var query = new FindClosestDataQuery(queryKey, queryResultSize);
        var result = handler.handle(query);

        assertThat(result.getResults())
                .containsExactlyInAnyOrder("ID_1", "ID_2", "ID_3", "ID_4");
    }

    @Test
    void shouldThrowException()
            throws EngineDataFinderFailedException {
        var queryKey = new BigDecimal[]{new BigDecimal("0")};
        var queryResultSize = 4;

        var engine = mock(EngineDataFinder.class);
        var engineDataKey = new EngineDataResourceKey(queryKey);
        var engineException = new EngineDataFinderFailedException("MOCKED ERROR");
        when(engine.find(engineDataKey, queryResultSize))
                .thenThrow(engineException);

        var handler = new FindClosesDataQueryHandler(engine);

        var query = new FindClosestDataQuery(queryKey, queryResultSize);
        assertThatThrownBy(() -> handler.handle(query))
                .isInstanceOf(FindClosesDataQueryHandlerFailedException.class)
                .hasMessage("could not find data for given key")
                .hasCause(engineException);
    }
}
