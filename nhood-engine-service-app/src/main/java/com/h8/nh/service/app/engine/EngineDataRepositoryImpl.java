package com.h8.nh.service.app.engine;

import com.h8.nh.nhoodengine.core.DataResource;
import com.h8.nh.nhoodengine.matrix.DataMatrixRepositoryFailedException;
import com.h8.nh.nhoodengine.matrix.DataMatrixResourceIterator;
import com.h8.nh.nhoodengine.matrix.impl.model.DataMatrixCell;
import com.h8.nh.nhoodengine.matrix.impl.model.DataMatrixCellConfiguration;
import com.h8.nh.nhoodengine.matrix.impl.model.DataMatrixCellFactory;
import com.h8.nh.nhoodengine.matrix.impl.model.DataMatrixCellIterator;

public class EngineDataRepositoryImpl implements EngineDataRepository {
    private final int metadataSize;
    private final DataMatrixCell<DataResource<EngineDataResourceKey, EngineData>> cell;

    public EngineDataRepositoryImpl(int metadataSize) {
        this.metadataSize = metadataSize;
        this.cell = DataMatrixCellFactory.root(metadataSize, DataMatrixCellConfiguration.builder().build());
    }

    @Override
    public int getMetadataSize() {
        return this.metadataSize;
    }

    @Override
    public void add(DataResource<EngineDataResourceKey, EngineData> dataResource) throws DataMatrixRepositoryFailedException {
        cell.add(dataResource);
    }

    @Override
    public DataMatrixResourceIterator<EngineDataResourceKey, EngineData> findNeighbours(EngineDataResourceKey engineDataResourceKey)
            throws DataMatrixRepositoryFailedException {
        DataMatrixCellIterator.startWith(engineDataResourceKey.unified(), this.cell);
        return null;
    }
}
