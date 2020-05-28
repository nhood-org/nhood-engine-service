package com.h8.nh.service.app.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindClosestDataQuery {
    private int data;
    private int resultSize;
}
