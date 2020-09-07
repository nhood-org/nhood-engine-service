package com.h8.nh.service.app.engine.adapter;

import com.h8.nh.nhoodengine.matrix.impl.DataMatrixCellBasedRepository;
import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataRepositoryException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class EngineDataRepositoryImplTest {

    @Test
    void shouldStoreAddedData()
            throws EngineDataRepositoryException {
        var testDataSize = 3;

        var wrappedRepository = new DataMatrixCellBasedRepository<EngineDataResourceKey, EngineData>(testDataSize);
        var repository = new EngineDataRepositoryImpl(wrappedRepository);

        var testData = Lists.list(
                testEngineData("ID_0", 0, 0, 0),
                testEngineData("ID_1", 1, 0, 0),
                testEngineData("ID_2", 0, 1, 0),
                testEngineData("ID_3", 0, 0, 1),
                testEngineData("ID_4", 1, 1, 0),
                testEngineData("ID_5", 1, 0, 1),
                testEngineData("ID_6", 1, 1, 1)
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

    private EngineData testEngineData(String id, int v1, int v2, int v3) {
        return EngineData.of(id, new BigDecimal[]{
                new BigDecimal(v1),
                new BigDecimal(v2),
                new BigDecimal(v3)});
    }
}
