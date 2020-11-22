package com.h8.nh.service.app.engine.adapter;

import com.h8.nh.nhoodengine.core.DataResource;
import com.h8.nh.nhoodengine.matrix.DataMatrixRepositoryFailedException;
import com.h8.nh.nhoodengine.matrix.impl.DataMatrixCellBasedRepository;
import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataFinderException;
import org.junit.jupiter.api.Test;

import static com.h8.nh.service.app.utils.TestUtils.testEngineData;
import static org.assertj.core.api.Assertions.assertThat;

public class EngineDataFinderImplTest {

    @Test
    void shouldReturnClosestFromInjectedRepository()
            throws DataMatrixRepositoryFailedException, EngineDataFinderException {
        var testDataSize = 3;
        var testData = new EngineData[]{
                testEngineData(0, 0, 0, "URL_0"),
                testEngineData(1, 0, 0, "URL_1"),
                testEngineData(0, 1, 0, "URL_2"),
                testEngineData(0, 0, 1, "URL_3"),
                testEngineData(1, 1, 0, "URL_4"),
                testEngineData(1, 0, 1, "URL_5"),
                testEngineData(1, 1, 1, "URL_6")
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
                .extracting("reference")
                .containsExactlyInAnyOrder("URL_0", "URL_1", "URL_2", "URL_3");
    }
}
