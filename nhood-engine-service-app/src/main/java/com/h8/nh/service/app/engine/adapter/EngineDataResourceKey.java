package com.h8.nh.service.app.engine.adapter;

import com.h8.nh.nhoodengine.core.DataResourceKey;
import com.h8.nh.service.app.engine.EngineData;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Arrays;

@EqualsAndHashCode
public class EngineDataResourceKey implements DataResourceKey {
    private final BigDecimal[] key;

    private EngineDataResourceKey(BigDecimal[] key) {
        this.key = key;
    }

    public static EngineDataResourceKey of(BigDecimal[] key) {
        var k = Arrays.copyOf(key, key.length);
        return new EngineDataResourceKey(k);
    }

    public static EngineDataResourceKey from(EngineData data) {
        return new EngineDataResourceKey(data.getKey());
    }

    @Override
    public BigDecimal[] unified() {
        return Arrays.copyOf(key, key.length);
    }
}
