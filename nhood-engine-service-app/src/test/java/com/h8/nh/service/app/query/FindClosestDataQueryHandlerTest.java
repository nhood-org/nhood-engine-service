package com.h8.nh.service.app.query;

import com.h8.nh.service.app.engine.EngineDataFinder;
import com.h8.nh.service.app.engine.EngineDataFinderException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.h8.nh.service.app.utils.TestUtils.testEngineData;
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
                testEngineData(0, 0, 0, "URL_0"),
                testEngineData(0, 0, 0, "URL_1"),
                testEngineData(0, 0, 0, "URL_2"),
                testEngineData(0, 0, 0, "URL_3")
        );
        when(engine.find(queryKey, queryResultSize))
                .thenReturn(engineData);

        var handler = new FindClosestDataQueryHandler(engine);

        var query = new FindClosestDataQuery(queryKey, queryResultSize);
        var result = handler.handle(query);

        assertThat(result.getResults())
                .extracting("reference.url")
                .containsExactlyInAnyOrder("URL_0", "URL_1", "URL_2", "URL_3");
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
