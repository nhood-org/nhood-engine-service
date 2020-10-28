package com.h8.nh.service.app.engine.adapter;

import com.h8.nh.nhoodengine.core.DataResource;
import com.h8.nh.nhoodengine.matrix.DataMatrixRepositoryFailedException;
import com.h8.nh.nhoodengine.matrix.impl.DataMatrixCellBasedRepository;
import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataFinderException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class EngineDataFinderImplTest {

    @Test
    void shouldReturnClosestFromInjectedRepository()
            throws DataMatrixRepositoryFailedException, EngineDataFinderException {
        var testDataSize = 3;
        var testData = new EngineData[]{
                testEngineData(0, 0, 0, "DATA_0"),
                testEngineData(1, 0, 0, "DATA_1"),
                testEngineData(0, 1, 0, "DATA_2"),
                testEngineData(0, 0, 1, "DATA_3"),
                testEngineData(1, 1, 0, "DATA_4"),
                testEngineData(1, 0, 1, "DATA_5"),
                testEngineData(1, 1, 1, "DATA_6")
        };

        var repository = new DataMatrixCellBasedRepository<EngineDataResourceKey, EngineData>(testDataSize);
        for (var d : testData) {
            var r = DataResource.<EngineDataResourceKey, EngineData>builder()
                    .key(EngineDataResourceKey.from(d))
                    .data(d)
                    .build();
            repository.add(r);
        }

        var finder = new EngineDataFinderImpl(repository);
        var result = finder.find(testData[0].getKey(), 4);

        assertThat(result)
                .extracting("data")
                .containsExactlyInAnyOrder("DATA_0", "DATA_1", "DATA_2", "DATA_3");
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
