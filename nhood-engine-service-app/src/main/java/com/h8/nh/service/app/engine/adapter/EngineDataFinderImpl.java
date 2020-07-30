package com.h8.nh.service.app.engine.adapter;

import com.h8.nh.nhoodengine.core.DataFinder;
import com.h8.nh.nhoodengine.core.DataFinderCriteria;
import com.h8.nh.nhoodengine.core.DataFinderFailedException;
import com.h8.nh.nhoodengine.core.impl.DataScoreComputationEngine;
import com.h8.nh.nhoodengine.matrix.DataMatrixRepository;
import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataFinder;
import com.h8.nh.service.app.engine.EngineDataFinderFailedException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class EngineDataFinderImpl implements EngineDataFinder {

    private final DataFinder<EngineDataResourceKey, EngineData> finder;

    public EngineDataFinderImpl(DataMatrixRepository<EngineDataResourceKey, EngineData> repository) {
        this.finder = new DataScoreComputationEngine<>(repository);
    }

    @Override
    public List<EngineData> find(BigDecimal[] key, int limit)
            throws EngineDataFinderFailedException {
        var criteria = DataFinderCriteria.<EngineDataResourceKey>builder()
                .metadata(EngineDataResourceKey.of(key))
                .limit(limit)
                .build();
        try {
            var result = finder.find(criteria);
            return result.stream()
                    .map(r -> r.getResource().getData())
                    .collect(Collectors.toList());
        } catch (DataFinderFailedException e) {
            throw new EngineDataFinderFailedException(
                    "could not get result data from the negine", e);
        }
    }
}
