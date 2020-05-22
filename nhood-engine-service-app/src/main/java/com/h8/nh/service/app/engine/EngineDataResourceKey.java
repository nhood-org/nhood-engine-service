package com.h8.nh.service.app.engine;

import com.h8.nh.nhoodengine.core.DataResourceKey;
import jdk.jshell.spi.ExecutionControl;

import java.math.BigDecimal;

public class EngineDataResourceKey implements DataResourceKey {

    public static final int METADATA_SIZE = 1;
    private final int value;

    public EngineDataResourceKey(int value) {
        this.value = value;
    }

    @Override
    public BigDecimal[] unified() {
        BigDecimal[] bd = new BigDecimal[METADATA_SIZE];
        bd[0] = new BigDecimal(value);
        return bd;
    }
}
