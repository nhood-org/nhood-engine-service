package com.h8.nh.service.app.command;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Arrays;

@EqualsAndHashCode
public class AddDataCommand {
    private final String id;
    private final BigDecimal[] key;

    private AddDataCommand(String id, BigDecimal[] key) {
        this.id = id;
        this.key = key;
    }

    public static AddDataCommand of(String id, BigDecimal[] key) {
        return new AddDataCommand(id, key);
    }

    public String getId() {
        return id;
    }

    public BigDecimal[] getKey() {
        return Arrays.copyOf(key, key.length);
    }
}
