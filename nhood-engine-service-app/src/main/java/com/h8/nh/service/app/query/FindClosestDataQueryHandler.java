package com.h8.nh.service.app.query;

import com.h8.nh.service.app.engine.EngineDataFinder;
import com.h8.nh.service.app.engine.EngineDataFinderException;

public class FindClosestDataQueryHandler implements FindClosestDataQueryHandlerAPI {

    private final EngineDataFinder engineDataFinder;

    public FindClosestDataQueryHandler(EngineDataFinder engineDataFinder) {
        this.engineDataFinder = engineDataFinder;
    }

    public FindClosestDataQueryResult handle(FindClosestDataQuery query)
            throws FindClosestDataQueryHandlerException {
        try {
            var results = engineDataFinder.find(query.getKey(), query.getResultSize());
            return new FindClosestDataQueryResult(results);
        } catch (EngineDataFinderException e) {
            throw new FindClosestDataQueryHandlerException(
                    "Could not find data for given key", e);
        }
    }
}
