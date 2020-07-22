package com.h8.nh.service.app.query;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Arrays;

@EqualsAndHashCode
public class FindClosestDataQuery {
    private final BigDecimal[] key;
    private final int resultSize;

    public FindClosestDataQuery(BigDecimal[] key, int resultSize) {
        this.key = Arrays.copyOf(key, key.length);
        this.resultSize = resultSize;
    }

    public BigDecimal[] getKey() {
        return Arrays.copyOf(key, key.length);
    }

    public int getResultSize() {
        return resultSize;
    }
}
