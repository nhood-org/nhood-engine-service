package com.h8.nh.service.port.webflux;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
public class ClosestData {
    private int[] data;

    public ClosestData(int[] data) {
        this.data = copy(data);
    }

    public int[] getData() {
        return copy(data);
    }

    public void setData(int[] data) {
        this.data = copy(data);
    }

    private int[] copy(int[] d) {
        return Arrays.copyOf(d, d.length);
    }
}
