package com.h8.nh.service.app.query;

public class FindClosestDataQueryHandler {

    public FindClosestDataQueryResult handle(FindClosestDataQuery query) {
        var id = query.getId();
        return new FindClosestDataQueryResult(id);
    }
}
