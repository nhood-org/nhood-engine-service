package com.h8.nh.service.app.query;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoopFindClosestDataQueryResult {
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NoopFindClosestDataQueryResult that = (NoopFindClosestDataQueryResult) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
