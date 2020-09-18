package com.h8.nh.service.port.webflux;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

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

    public String[] getKey() {
        return Arrays.copyOf(key, key.length);
    }

    @JsonIgnore
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EngineDataDTO that = (EngineDataDTO) o;
        return Objects.equals(id, that.id)
                && Arrays.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id);
        result = 31 * result + Arrays.hashCode(key);
        return result;
    }
}
