package com.h8.nh.service.app.query;

import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.EngineDataFinder;
import com.h8.nh.service.app.engine.EngineDataFinderException;

import java.util.List;
import java.util.stream.Collectors;

public class FindClosesDataQueryHandler {

    private final EngineDataFinder engineDataFinder;

    public FindClosesDataQueryHandler(EngineDataFinder engineDataFinder) {
        this.engineDataFinder = engineDataFinder;
    }

    public FindClosestDataQueryResult handle(FindClosestDataQuery query)
            throws FindClosesDataQueryHandlerException {
        try {
            var foundData = engineDataFinder.find(query.getKey(), query.getResultSize());
            return mapResultFrom(foundData);
        } catch (EngineDataFinderException e) {
            throw new FindClosesDataQueryHandlerException(
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
