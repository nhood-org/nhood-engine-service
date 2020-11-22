package com.h8.nh.service.port.webflux;

import com.h8.nh.service.dto.EngineDataDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AddDataRequestHandler {
    Mono<UUID> add(EngineDataDTO data) throws WebFluxAPIException;
}
