package com.h8.nh.service.port.webflux;

import com.h8.nh.service.app.AppAPI;
import com.h8.nh.service.app.query.FindClosestDataQuery;
import com.h8.nh.service.app.query.FindClosestDataQueryHandlerException;
import com.h8.nh.service.dto.EngineDataDTO;
import reactor.core.publisher.Flux;

public class FindDataRequestHandlerWebFluxAdapter implements FindDataRequestHandler {

    private final AppAPI app;

    public FindDataRequestHandlerWebFluxAdapter(AppAPI app) {
        this.app = app;
    }

    public Flux<EngineDataDTO> find(EngineDataDTO data, int size)
            throws WebFluxAPIException {
        var query = new FindClosestDataQuery(data.getBigDecimalKey(), size);
        try {
            var result = app.handle(query);
            var resultStream = result.getResults()
                    .stream()
                    .map(r -> EngineDataDTO.from(
                            r.getUuid(),
                            r.getKey(),
                            r.getReference())
                    );
            return Flux.fromStream(resultStream);
        } catch (FindClosestDataQueryHandlerException e) {
            throw new WebFluxAPIException("Could not execute application find-closest-data query", e);
        }
    }
}
