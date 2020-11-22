package com.h8.nh.service.port.webflux;

import com.h8.nh.service.dto.EngineDataDTO;
import reactor.core.publisher.Flux;

public interface FindDataRequestHandler {
    Flux<EngineDataDTO> find(EngineDataDTO data, int size) throws WebFluxAPIException;
}
