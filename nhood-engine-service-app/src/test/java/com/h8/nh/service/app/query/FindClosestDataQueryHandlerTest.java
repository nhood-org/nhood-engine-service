package com.h8.nh.service.app.query;

import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataFinder;
import com.h8.nh.service.app.engine.EngineDataFinderException;
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
            throws EngineDataFinderException, FindClosestDataQueryHandlerException {
        var queryKey = new BigDecimal[]{};
        var queryResultSize = 4;

        var engine = mock(EngineDataFinder.class);
        var engineData = Arrays.asList(
                EngineData.of(new BigDecimal[]{}, "DATA_0"),
                EngineData.of(new BigDecimal[]{}, "DATA_1"),
                EngineData.of(new BigDecimal[]{}, "DATA_2"),
                EngineData.of(new BigDecimal[]{}, "DATA_3")
        );
        when(engine.find(queryKey, queryResultSize))
                .thenReturn(engineData);

        var handler = new FindClosestDataQueryHandler(engine);

        var query = new FindClosestDataQuery(queryKey, queryResultSize);
        var result = handler.handle(query);

        assertThat(result.getResults())
                .extracting("data")
                .containsExactlyInAnyOrder("DATA_0", "DATA_1", "DATA_2", "DATA_3");
    }

    @Test
    void shouldThrowException()
            throws EngineDataFinderException {
        var queryKey = new BigDecimal[]{};
        var queryResultSize = 4;

        var engine = mock(EngineDataFinder.class);
        var engineException = new EngineDataFinderException("MOCKED ERROR");
        when(engine.find(queryKey, queryResultSize))
                .thenThrow(engineException);

        var handler = new FindClosestDataQueryHandler(engine);

        var query = new FindClosestDataQuery(queryKey, queryResultSize);
        assertThatThrownBy(() -> handler.handle(query))
                .isInstanceOf(FindClosestDataQueryHandlerException.class)
                .hasMessage("Could not find data for given key")
                .hasCause(engineException);
    }
}
