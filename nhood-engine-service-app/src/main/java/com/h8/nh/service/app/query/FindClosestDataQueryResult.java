package com.h8.nh.service.app.query;

import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class FindClosestDataQueryResult {
    private final List<String> results;

    public FindClosestDataQueryResult(List<String> results) {
        this.results = List.copyOf(results);
    }

    public List<String> getResults() {
        return List.copyOf(results);
    }
}
