package com.h8.nh.service.app.query;

public class NoopFindClosestDataQueryHandler {

    public NoopFindClosestDataQueryResult handle(NoopFindClosestDataQuery query) {
        var id = query.getId();
        return new NoopFindClosestDataQueryResult(id);
    }
}
