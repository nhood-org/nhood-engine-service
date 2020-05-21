package com.h8.nh.service.app.query;

import lombok.EqualsAndHashCode;

import java.util.Arrays;

@EqualsAndHashCode
public class FindClosestDataQueryResult {

    private final int[] results;

    public FindClosestDataQueryResult(int[] results) {
        this.results = copyArray(results);
    }

    public int[] getResults() {
        return copyArray(results);
    }

    private int[] copyArray(int[] a) {
        return Arrays.copyOf(a, a.length);
    }
}
