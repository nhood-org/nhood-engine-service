package com.h8.nh.service.app.engine.adapter;

import com.h8.nh.nhoodengine.matrix.impl.DataMatrixCellBasedRepository;
import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataRepositoryException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static com.h8.nh.service.app.utils.TestUtils.testEngineData;
import static org.assertj.core.api.Assertions.assertThat;

class EngineDataRepositoryImplTest {

    @Test
    void shouldStoreAddedData()
            throws EngineDataRepositoryException {
        var testDataSize = 3;

        var wrappedRepository = new DataMatrixCellBasedRepository<EngineDataResourceKey, EngineData>(testDataSize);
        var repository = new EngineDataRepositoryImpl(wrappedRepository);

        var testData = Lists.list(
                testEngineData(UUID.randomUUID(),0, 0, 0, "URL_0"),
                testEngineData(UUID.randomUUID(),1, 0, 0, "URL_1"),
                testEngineData(UUID.randomUUID(),0, 1, 0, "URL_2"),
                testEngineData(UUID.randomUUID(),0, 0, 1, "URL_3"),
                testEngineData(UUID.randomUUID(),1, 1, 0, "URL_4"),
                testEngineData(UUID.randomUUID(),1, 0, 1, "URL_5"),
                testEngineData(UUID.randomUUID(),1, 1, 1, "URL_6")
        );
        for (var d : testData) {
            repository.add(d);
        }

        var testDataKey = EngineDataResourceKey.from(testData.get(0));
        var testDataNeighbours = wrappedRepository.findNeighbours(testDataKey);

        var storedTestData = new ArrayList<EngineData>();
        while (testDataNeighbours.hasNext()) {
            for (var n : testDataNeighbours.next()) {
                storedTestData.add(n.getData());
            }
        }

        assertThat(storedTestData).containsExactlyInAnyOrderElementsOf(testData);
    }

    @Test
    void shouldAmendAddedDataWithUUID()
            throws EngineDataRepositoryException {
        var testDataSize = 3;

        var wrappedRepository = new DataMatrixCellBasedRepository<EngineDataResourceKey, EngineData>(testDataSize);
        var repository = new EngineDataRepositoryImpl(wrappedRepository);

        var testData = testEngineData(0, 0, 0, "URL_0");
        repository.add(testData);

        var testDataKey = EngineDataResourceKey.from(testData);
        var testDataNeighbours = wrappedRepository.findNeighbours(testDataKey);

        var storedTestData = new ArrayList<EngineData>();
        while (testDataNeighbours.hasNext()) {
            for (var n : testDataNeighbours.next()) {
                storedTestData.add(n.getData());
            }
        }

        assertThat(storedTestData).hasSize(1);
        var expectedTestData = testEngineData(
                storedTestData.get(0).getUuid(),
                0, 0,0,
                testData.getReference()
        );

        assertThat(storedTestData).containsExactly(expectedTestData);
    }
}
