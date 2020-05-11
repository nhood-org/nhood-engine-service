package com.h8.nh.service.app.query;

public class FindClosestDataQueryHandler {

    public FindClosestDataQueryResult handle(FindClosestDataQuery command) {
        var id = command.getId();
        return new FindClosestDataQueryResult(id);
    }
}
