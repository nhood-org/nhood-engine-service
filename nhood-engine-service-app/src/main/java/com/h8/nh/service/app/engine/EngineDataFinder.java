package com.h8.nh.service.app.engine;

import java.util.List;

public interface EngineDataFinder {
    List<EngineData> find(EngineDataResourceKey key, int limit)
            throws EngineDataFinderFailedException;
}
