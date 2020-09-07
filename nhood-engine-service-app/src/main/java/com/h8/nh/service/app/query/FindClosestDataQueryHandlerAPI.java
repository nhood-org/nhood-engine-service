package com.h8.nh.service.app.query;

public interface FindClosestDataQueryHandlerAPI {
    FindClosestDataQueryResult handle(FindClosestDataQuery query)
            throws FindClosestDataQueryHandlerException;
}
