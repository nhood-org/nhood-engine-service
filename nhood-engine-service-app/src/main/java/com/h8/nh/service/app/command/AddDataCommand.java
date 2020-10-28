package com.h8.nh.service.app.command;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Arrays;

@EqualsAndHashCode
public class AddDataCommand {
    private final BigDecimal[] key;
    private final String reference;

    private AddDataCommand(BigDecimal[] key, String reference) {
        this.key = key;
        this.reference = reference;
    }

    public static AddDataCommand of(BigDecimal[] key, String reference) {
        return new AddDataCommand(key, reference);
    }

    public BigDecimal[] getKey() {
        return Arrays.copyOf(key, key.length);
    }

    public String getReference() {
        return reference;
    }
}
