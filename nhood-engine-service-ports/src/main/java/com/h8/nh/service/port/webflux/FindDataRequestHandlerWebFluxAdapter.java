package com.h8.nh.service.port.webflux;

import com.h8.nh.service.app.AppAPI;
import com.h8.nh.service.app.query.FindClosestDataQuery;
import com.h8.nh.service.app.query.FindClosestDataQueryHandlerException;
import reactor.core.publisher.Flux;

public class FindDataRequestHandlerWebFluxAdapter implements FindDataRequestHandler {

    private final AppAPI app;

    public FindDataRequestHandlerWebFluxAdapter(AppAPI app) {
        this.app = app;
    }

    public Flux<EngineDataDTO> find(EngineDataDTO data, int size)
            throws WebFluxAPIException, WebFluxAPIBadRequestException {
        if (size <= 0) {
            throw new WebFluxAPIBadRequestException("Requested data size must be positive");
        }

        var query = new FindClosestDataQuery(data.getBigDecimalKey(), size);
        try {
            var result = app.handle(query);
            var resultStream = result.getResults()
                    .stream()
                    .map(r -> EngineDataDTO.from(r.getId(), r.getKey()));
            return Flux.fromStream(resultStream);
        } catch (FindClosestDataQueryHandlerException e) {
            throw new WebFluxAPIException("Could not execute application find-closest-data query", e);
        }
    }
}
