package com.h8.nh.service.app.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class FindClosestDataQuery {
    private final int metadata;
    private final int size;
}
