package com.h8.nh.service.port.webflux;

import reactor.core.publisher.Mono;

public interface ClosestDataFinder {
    Mono<ClosestDataDTO> findByID(int id) throws ClosestDataFinderException;
}
