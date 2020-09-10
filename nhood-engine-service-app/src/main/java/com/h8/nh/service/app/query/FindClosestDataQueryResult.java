package com.h8.nh.service.app.query;

import com.h8.nh.service.app.engine.EngineData;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class FindClosestDataQueryResult {
    private final List<EngineData> results;

    public FindClosestDataQueryResult(List<EngineData> results) {
        this.results = List.copyOf(results);
    }

    public List<EngineData> getResults() {
        return List.copyOf(results);
    }
}
