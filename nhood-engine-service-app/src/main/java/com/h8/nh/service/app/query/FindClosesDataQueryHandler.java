package com.h8.nh.service.app.query;

import com.h8.nh.service.app.engine.EngineDataFinder;

public class FindClosesDataQueryHandler {

    private final EngineDataFinder engineDataFinder;

    public FindClosesDataQueryHandler(EngineDataFinder engineDataFinder) {
        this.engineDataFinder = engineDataFinder;
    }

    public FindClosestDataQueryResult handle(FindClosestDataQuery query) {
        var result = new int[]{};

        // TODO: just some fake usage of engine data finder
        var foo = engineDataFinder.hashCode();
        System.out.println(foo);

        return new FindClosestDataQueryResult(result);
    }
}
