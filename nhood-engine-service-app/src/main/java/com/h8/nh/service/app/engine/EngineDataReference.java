package com.h8.nh.service.app.engine;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EngineDataReference {
    private final String url;

    private EngineDataReference(String url) {
        this.url = url;
    }

    public static EngineDataReference of(String url) {
        return new EngineDataReference(url);
    }

    public String getUrl() {
        return url;
    }
}
