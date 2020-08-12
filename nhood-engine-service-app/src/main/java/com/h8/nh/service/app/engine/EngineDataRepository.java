package com.h8.nh.service.app.engine;

public interface EngineDataRepository {
    void add(EngineData data)
            throws EngineDataRepositoryException;
}
