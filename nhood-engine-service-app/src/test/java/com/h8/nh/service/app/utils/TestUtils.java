package com.h8.nh.service.app.utils;

import com.h8.nh.service.app.engine.EngineData;

import java.math.BigDecimal;
import java.util.UUID;

public class TestUtils {
    public static EngineData testEngineData(int v1, int v2, int v3, String reference) {
        return EngineData.of(
                new BigDecimal[]{
                        new BigDecimal(v1),
                        new BigDecimal(v2),
                        new BigDecimal(v3)
                }, reference);
    }

    public static EngineData testEngineData(UUID uuid, int v1, int v2, int v3, String reference) {
        return EngineData.of(
                uuid,
                new BigDecimal[]{
                        new BigDecimal(v1),
                        new BigDecimal(v2),
                        new BigDecimal(v3)
                }, reference);
    }
}
