package com.h8.nh.service.port.webflux;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Arrays;

@EqualsAndHashCode
public class EngineDataDTO {

    private String id;
    private String[] key;

    public EngineDataDTO() {
    }

    public EngineDataDTO(String id, String[] key) {
        this.id = id;
        this.key = Arrays.copyOf(key, key.length);
    }

    public static EngineDataDTO from(String id, BigDecimal[] key) {
        var k = new String[key.length];

        var i = 0;
        for (var v : key) {
            k[i] = v.toString();
            i++;
        }

        return new EngineDataDTO(id, k);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal[] getBigDecimalKey() {
        var k = new BigDecimal[key.length];

        var i = 0;
        for (var v : key) {
            k[i] = new BigDecimal(v);
            i++;
        }

        return k;
    }

    public void setKey(String[] key) {
        this.key = Arrays.copyOf(key, key.length);
    }
}
