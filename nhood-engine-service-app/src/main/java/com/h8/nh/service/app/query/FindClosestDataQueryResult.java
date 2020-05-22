package com.h8.nh.service.app.query;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class FindClosestDataQueryResult {
    private final List<Integer> values;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        values.forEach(val -> stringBuilder.append(val + ", "));
        return "FindClosestDataQueryResult{" +
                "values=" + stringBuilder.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindClosestDataQueryResult that = (FindClosestDataQueryResult) o;
        return values.equals(that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
