package com.h8.nh.service.app.command;

import com.h8.nh.service.app.engine.EngineDataRepository;
import com.h8.nh.service.app.engine.EngineDataRepositoryException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.h8.nh.service.app.utils.TestUtils.testEngineData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class AddDataCommandHandlerTest {

    @Test
    void shouldAddDataToRepository()
            throws AddDataCommandHandlerException, EngineDataRepositoryException {
        var data = testEngineData(0, 0, 0, "URL_1");

        var repository = mock(EngineDataRepository.class);
        var handler = new AddDataCommandHandler(repository);

        var cmd = AddDataCommand.of(data.getKey(), data.getReference());
        var cmdResult = handler.handle(cmd);
        assertThat(cmdResult).isNotNull();

        Mockito.verify(repository, times(1)).add(data);
    }
}
