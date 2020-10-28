package com.h8.nh.service.app.utils;

import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataReference;

import java.math.BigDecimal;

public class TestUtils {
    public static EngineData testEngineData(int v1, int v2, int v3, String url) {
        EngineDataReference reference = EngineDataReference.of(url);
        return EngineData.of(
                new BigDecimal[]{
                        new BigDecimal(v1),
                        new BigDecimal(v2),
                        new BigDecimal(v3)
                }, reference);
    }
}
