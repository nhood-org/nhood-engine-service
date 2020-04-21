package com.h8.nh.service.app.command;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class FindClosestDataCommandHandlerTest {

    @Test
    void shouldHandleCommand() {
        var finder = new FindClosestDataCommandHandler();

        var id = new Random().nextInt();
        var cmd = new FindClosestDataCommand(id);
        var result = finder.handle(cmd);

        var expectedResult = new FindClosestDataCommandResult(id);
        assertThat(result).isEqualTo(expectedResult);
    }
}
