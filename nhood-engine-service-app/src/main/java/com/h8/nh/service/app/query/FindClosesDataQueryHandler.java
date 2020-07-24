package com.h8.nh.service.app.query;

import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataFinder;
import com.h8.nh.service.app.engine.EngineDataFinderFailedException;

import java.util.List;
import java.util.stream.Collectors;

public class FindClosesDataQueryHandler {

    private final EngineDataFinder engineDataFinder;

    public FindClosesDataQueryHandler(EngineDataFinder engineDataFinder) {
        this.engineDataFinder = engineDataFinder;
    }

    public FindClosestDataQueryResult handle(FindClosestDataQuery query)
            throws FindClosesDataQueryHandlerFailedException {
        try {
            var foundData = engineDataFinder.find(query.getKey(), query.getResultSize());
            return mapResultFrom(foundData);
        } catch (EngineDataFinderFailedException e) {
            throw new FindClosesDataQueryHandlerFailedException(
                    "could not find data for given key", e);
        }
    }

    private FindClosestDataQueryResult mapResultFrom(List<EngineData> data) {
        var results = data
                .stream()
                .map(EngineData::getId)
                .collect(Collectors.toList());
        return new FindClosestDataQueryResult(results);
    }
}
