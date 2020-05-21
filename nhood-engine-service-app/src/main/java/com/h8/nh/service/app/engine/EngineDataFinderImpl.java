package com.h8.nh.service.app.engine;

import com.h8.nh.nhoodengine.core.DataFinderCriteria;
import com.h8.nh.nhoodengine.core.DataFinderFailedException;
import com.h8.nh.nhoodengine.core.DataFinderResult;

import java.util.List;

// TODO: consider wrapping nhood library implementation
public class EngineDataFinderImpl implements EngineDataFinder {

    private final EngineDataRepository engineDataRepository;

    public EngineDataFinderImpl(EngineDataRepository engineDataRepository) {
        this.engineDataRepository = engineDataRepository;
    }

    @Override
    public List<DataFinderResult<EngineDataResourceKey, EngineData>> find(DataFinderCriteria<EngineDataResourceKey> dataFinderCriteria)
            throws DataFinderFailedException {

        // TODO: just some fake usage of engine data finder
        var foo = engineDataRepository.hashCode();
        System.out.println(foo);

        return null;
    }
}
