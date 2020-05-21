package com.h8.nh.service.app.engine;

import com.h8.nh.nhoodengine.core.DataResource;
import com.h8.nh.nhoodengine.matrix.DataMatrixRepositoryFailedException;
import com.h8.nh.nhoodengine.matrix.DataMatrixResourceIterator;

// TODO: consider wrapping nhood library implementation
public class EngineDataRepositoryImpl implements EngineDataRepository {

    @Override
    public int getMetadataSize() {
        // TODO!!! implement me
        return 0;
    }

    @Override
    public void add(DataResource<EngineDataResourceKey, EngineData> dataResource)
            throws DataMatrixRepositoryFailedException {
        // TODO!!! implement me
    }

    @Override
    public DataMatrixResourceIterator<EngineDataResourceKey, EngineData> findNeighbours(EngineDataResourceKey engineDataResourceKey)
            throws DataMatrixRepositoryFailedException {
        // TODO!!! implement me
        return null;
    }
}
