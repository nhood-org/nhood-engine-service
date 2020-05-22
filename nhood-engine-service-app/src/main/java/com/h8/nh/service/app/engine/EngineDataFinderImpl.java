package com.h8.nh.service.app.engine;

import com.h8.nh.nhoodengine.core.DataFinder;
import com.h8.nh.nhoodengine.core.DataFinderCriteria;
import com.h8.nh.nhoodengine.core.DataFinderFailedException;
import com.h8.nh.nhoodengine.core.DataFinderResult;
import com.h8.nh.nhoodengine.matrix.DataMatrixRepositoryFailedException;
import com.h8.nh.nhoodengine.matrix.DataMatrixResourceIterator;

import java.util.Collections;
import java.util.List;

public class EngineDataFinderImpl implements EngineDataFinder {

    private final DataFinder<EngineDataResourceKey, EngineData> finder;

    public EngineDataFinderImpl(DataFinder<EngineDataResourceKey, EngineData> finder) {
        this.finder = finder;
    }

    @Override
    public List<DataFinderResult<EngineDataResourceKey, EngineData>> find(DataFinderCriteria<EngineDataResourceKey> dataFinderCriteria)
            throws DataFinderFailedException {
        return finder.find(dataFinderCriteria);
    }

}
