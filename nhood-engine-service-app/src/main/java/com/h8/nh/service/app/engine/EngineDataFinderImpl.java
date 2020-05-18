package com.h8.nh.service.app.engine;

import com.h8.nh.nhoodengine.core.DataFinderCriteria;
import com.h8.nh.nhoodengine.core.DataFinderFailedException;
import com.h8.nh.nhoodengine.core.DataFinderResult;

import java.util.List;

public class EngineDataFinderImpl implements EngineDataFinder {

//    private final EngineDataRepository engineDataRepository;
//
//    public EngineDataFinderImpl(EngineDataRepository engineDataRepository) {
//        this.engineDataRepository = engineDataRepository;
//    }

    @Override
    public List<DataFinderResult<EngineDataResourceKey, EngineData>> find(DataFinderCriteria<EngineDataResourceKey> dataFinderCriteria) throws DataFinderFailedException {
        // TODO!!! implement me
        return null;
    }
}
