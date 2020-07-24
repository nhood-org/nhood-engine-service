package com.h8.nh.service.app.engine;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Arrays;

@EqualsAndHashCode
public class EngineData {
    private final String id;
    private final BigDecimal[] key;

    private EngineData(String id, BigDecimal[] key) {
        this.id = id;
        this.key = key;
    }

    public static EngineData of(String id, BigDecimal[] key) {
        return new EngineData(id, key);
    }

    public String getId() {
        return id;
    }

    public BigDecimal[] getKey() {
        return Arrays.copyOf(key, key.length);
    }
}
