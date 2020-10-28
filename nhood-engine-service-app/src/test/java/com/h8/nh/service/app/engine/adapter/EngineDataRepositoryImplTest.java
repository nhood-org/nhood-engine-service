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
                testEngineData(0, 0, 0, "DATA_0"),
                testEngineData(1, 0, 0, "DATA_1"),
                testEngineData(0, 1, 0, "DATA_2"),
                testEngineData(0, 0, 1, "DATA_3"),
                testEngineData(1, 1, 0, "DATA_4"),
                testEngineData(1, 0, 1, "DATA_5"),
                testEngineData(1, 1, 1, "DATA_6")
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

    private EngineData testEngineData(int v1, int v2, int v3, String data) {
        return EngineData.of(
                new BigDecimal[]{
                        new BigDecimal(v1),
                        new BigDecimal(v2),
                        new BigDecimal(v3)
                }, data);
    }
}
