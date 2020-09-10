package com.h8.nh.service.port.webflux;

import reactor.core.publisher.Mono;

public interface AddDataRequestHandler {
    Mono<EngineDataDTO> add(EngineDataDTO data) throws WebFluxAPIException;
}
