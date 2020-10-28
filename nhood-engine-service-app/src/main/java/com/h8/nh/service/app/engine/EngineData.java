package com.h8.nh.service.app.engine;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

@EqualsAndHashCode
public class EngineData {
    private final UUID uuid;
    private final BigDecimal[] key;
    private final String data;

    private EngineData(UUID uuid, BigDecimal[] key, String data) {
        this.uuid = uuid;
        this.key = Arrays.copyOf(key, key.length);
        this.data = data;
    }

    public static EngineData of(BigDecimal[] key, String data) {
        return new EngineData(null, key, data);
    }

    public static EngineData of(UUID uuid, BigDecimal[] key, String data) {
        return new EngineData(uuid, key, data);
    }

    public UUID getUuid() {
        return uuid;
    }

    public BigDecimal[] getKey() {
        return Arrays.copyOf(key, key.length);
    }

    public String getData() {
        return data;
    }
}
