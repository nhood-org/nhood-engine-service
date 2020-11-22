package com.h8.nh.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class EngineDataDTO {

    @JsonSerialize(using=UUIDSerializer.class)
    @JsonDeserialize(using= UUIDDeserializer.class)
    private UUID uuid;
    private String[] key;
    private String reference;

    public EngineDataDTO() {
    }

    public EngineDataDTO(UUID uuid, String[] key, String reference) {
        this.uuid = uuid;
        this.key = Arrays.copyOf(key, key.length);
        this.reference = reference;
    }

    public EngineDataDTO(String[] key, String reference) {
        this.key = Arrays.copyOf(key, key.length);
        this.reference = reference;
    }

    public static EngineDataDTO from(UUID uuid, BigDecimal[] key, String reference) {
        var k = new String[key.length];

        var i = 0;
        for (var v : key) {
            k[i] = v.toString();
            i++;
        }

        return new EngineDataDTO(uuid, k, reference);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        EngineDataDTO that = (EngineDataDTO) o;
        return Objects.equals(uuid, that.uuid)
                && Arrays.equals(key, that.key)
                && Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(uuid, reference);
        result = 31 * result + Arrays.hashCode(key);
        return result;
    }
}
