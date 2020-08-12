package com.h8.nh.service.app.engine;

import java.math.BigDecimal;
import java.util.List;

public interface EngineDataFinder {
    List<EngineData> find(BigDecimal[] key, int limit)
            throws EngineDataFinderException;
}
