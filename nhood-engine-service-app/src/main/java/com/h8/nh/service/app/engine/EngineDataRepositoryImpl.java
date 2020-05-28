package com.h8.nh.service.app.engine;

import com.h8.nh.nhoodengine.core.DataResource;
import com.h8.nh.nhoodengine.matrix.DataMatrixRepository;
import com.h8.nh.nhoodengine.matrix.DataMatrixRepositoryFailedException;
import com.h8.nh.nhoodengine.matrix.DataMatrixResourceIterator;
import com.h8.nh.nhoodengine.matrix.impl.DataMatrixCellBasedRepository;

public class EngineDataRepositoryImpl implements EngineDataRepository {

    private final DataMatrixRepository<EngineDataResourceKey, EngineData> repository;

    public EngineDataRepositoryImpl(int metadataSize) {
        this.repository = new DataMatrixCellBasedRepository<>(metadataSize);
    }

    @Override
    public int getMetadataSize() {
        return this.repository.getMetadataSize();
    }

    @Override
    public void add(DataResource<EngineDataResourceKey, EngineData> dataResource)
            throws DataMatrixRepositoryFailedException {
        repository.add(dataResource);
    }

    @Override
    public DataMatrixResourceIterator<EngineDataResourceKey, EngineData> findNeighbours(EngineDataResourceKey engineDataResourceKey)
            throws DataMatrixRepositoryFailedException {
        return this.repository.findNeighbours(engineDataResourceKey);
    }
}
