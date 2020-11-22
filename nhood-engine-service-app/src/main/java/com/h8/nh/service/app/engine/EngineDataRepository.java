package com.h8.nh.service.app.engine;

import java.util.UUID;

public interface EngineDataRepository {
    UUID add(EngineData data)
            throws EngineDataRepositoryException;
}
