package com.h8.nh.service.app.command;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Arrays;

@EqualsAndHashCode
public class AddDataCommand {
    private final BigDecimal[] key;
    private final String data;

    private AddDataCommand(BigDecimal[] key, String data) {
        this.key = key;
        this.data = data;
    }

    public static AddDataCommand of(BigDecimal[] key, String data) {
        return new AddDataCommand(key, data);
    }

    public BigDecimal[] getKey() {
        return Arrays.copyOf(key, key.length);
    }

    public String getData() {
        return data;
    }
}
