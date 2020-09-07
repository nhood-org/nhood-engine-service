package com.h8.nh.service.app;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class AppAPIConfig {
    private static final String METADATA_VECTOR_SIZE_ENV_VARIABLE = "METADATA_VECTOR_SIZE";
    private static final String METADATA_VECTOR_SIZE_DEFAULT = "3";

    private int metadataVectorSize;

    static AppAPIConfig loadFromEnv() {
        var vectorSizeStr = System.getenv().getOrDefault(METADATA_VECTOR_SIZE_ENV_VARIABLE, METADATA_VECTOR_SIZE_DEFAULT);
        var vectorSize = Integer.parseInt(vectorSizeStr);

        var config = new AppAPIConfig();
        config.metadataVectorSize = vectorSize;

        return config;
    }

    public int getMetadataVectorSize() {
        return metadataVectorSize;
    }
}
