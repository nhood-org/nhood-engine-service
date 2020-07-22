package com.h8.nh.service.app.engine;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Arrays;

@EqualsAndHashCode
public class EngineDataResourceKey {
    private final BigDecimal[] key;

    public EngineDataResourceKey(BigDecimal[] key) {
        this.key = Arrays.copyOf(key, key.length);
    }

    public BigDecimal[] getKey() {
        return Arrays.copyOf(key, key.length);
    }
}
