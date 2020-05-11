package com.h8.nh.service.app.query;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class FindClosestDataQueryHandlerTest {

    @Test
    void shouldHandleCommand() {
        var finder = new FindClosestDataQueryHandler();

        var id = new Random().nextInt();
        var cmd = new FindClosestDataQuery(id);
        var result = finder.handle(cmd);

        var expectedResult = new FindClosestDataQueryResult(id);
        assertThat(result).isEqualTo(expectedResult);
    }
}
