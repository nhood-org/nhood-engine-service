package com.h8.nh.service.app.engine.adapter;

import com.h8.nh.nhoodengine.core.DataResource;
import com.h8.nh.nhoodengine.matrix.DataMatrixRepositoryFailedException;
import com.h8.nh.nhoodengine.matrix.impl.DataMatrixCellBasedRepository;
import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataRepository;
import com.h8.nh.service.app.engine.EngineDataRepositoryException;

import java.util.UUID;

public class EngineDataRepositoryImpl implements EngineDataRepository {

    private final DataMatrixCellBasedRepository<EngineDataResourceKey, EngineData> repository;

    public EngineDataRepositoryImpl(DataMatrixCellBasedRepository<EngineDataResourceKey, EngineData> repository) {
        this.repository = repository;
    }

    @Override
    public UUID add(EngineData data) throws EngineDataRepositoryException {
        var resource = DataResource.<EngineDataResourceKey, EngineData>builder()
                .key(EngineDataResourceKey.from(data))
                .data(data)
                .build();
        try {
            repository.add(resource);
            return resource.getUuid();
        } catch (DataMatrixRepositoryFailedException e) {
            throw new EngineDataRepositoryException("Could not add resource to the repository", e);
        }
    }
}
