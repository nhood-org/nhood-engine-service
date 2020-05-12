package com.h8.nh.service.app.query;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class NoopNoopFindClosestDataQueryHandlerTest {

    @Test
    void shouldHandleCommand() {
        var finder = new NoopFindClosestDataQueryHandler();

        var id = new Random().nextInt();
        var cmd = new NoopFindClosestDataQuery(id);
        var result = finder.handle(cmd);

        var expectedResult = new NoopFindClosestDataQueryResult(id);
        assertThat(result).isEqualTo(expectedResult);
    }
}
