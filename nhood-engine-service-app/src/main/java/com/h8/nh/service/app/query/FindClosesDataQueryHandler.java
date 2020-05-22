package com.h8.nh.service.app.query;

import com.h8.nh.nhoodengine.core.DataFinderCriteria;
import com.h8.nh.nhoodengine.core.DataFinderFailedException;
import com.h8.nh.service.app.engine.EngineDataFinder;
import com.h8.nh.service.app.engine.EngineDataResourceKey;

import java.util.stream.Collectors;

public class FindClosesDataQueryHandler {

    private final EngineDataFinder engineDataFinder;

    public FindClosesDataQueryHandler(EngineDataFinder engineDataFinder) {
        this.engineDataFinder = engineDataFinder;
    }

    public FindClosestDataQueryResult handle(FindClosestDataQuery query) throws DataFinderFailedException {

        DataFinderCriteria<EngineDataResourceKey> criteria =
                DataFinderCriteria.<EngineDataResourceKey>builder()
                .limit(5)
                .metadata(new EngineDataResourceKey(query.getData()))
                .build();
        var results = engineDataFinder.find(criteria);
        return new FindClosestDataQueryResult(results
                                                .stream()
                                                .map(val -> val.getResource().getData().getValue())
                                                .collect(Collectors.toList()));
    }
}
