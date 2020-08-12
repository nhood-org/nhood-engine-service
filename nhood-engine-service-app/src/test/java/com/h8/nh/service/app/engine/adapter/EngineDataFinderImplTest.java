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
                testEngineData("ID_0", 0,0,0),
                testEngineData("ID_1", 1,0,0),
                testEngineData("ID_2", 0,1,0),
                testEngineData("ID_3", 0,0,1),
                testEngineData("ID_4", 1,1,0),
                testEngineData("ID_5", 1,0,1),
                testEngineData("ID_6", 1,1,1),
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
                .extracting("id")
                .containsExactlyInAnyOrder("ID_0", "ID_1", "ID_2", "ID_3");
    }

    private EngineData testEngineData(String id, int v1, int v2, int v3) {
        return EngineData.of(id, new BigDecimal[]{
                new BigDecimal(v1),
                new BigDecimal(v2),
                new BigDecimal(v3)});
    }
}
