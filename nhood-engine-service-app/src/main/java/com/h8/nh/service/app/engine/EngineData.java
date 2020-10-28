package com.h8.nh.service.app.engine;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

@EqualsAndHashCode
public class EngineData {
    private final UUID uuid;
    private final BigDecimal[] key;
    private final EngineDataReference reference;

    private EngineData(UUID uuid, BigDecimal[] key, EngineDataReference reference) {
        this.uuid = uuid;
        this.key = Arrays.copyOf(key, key.length);
        this.reference = reference;
    }

    public static EngineData of(BigDecimal[] key, EngineDataReference reference) {
        return new EngineData(null, key, reference);
    }

    public static EngineData of(UUID uuid, BigDecimal[] key, EngineDataReference reference) {
        return new EngineData(uuid, key, reference);
    }

    public UUID getUuid() {
        return uuid;
    }

    public BigDecimal[] getKey() {
        return Arrays.copyOf(key, key.length);
    }

    public EngineDataReference getReference() {
        return reference;
    }
}
