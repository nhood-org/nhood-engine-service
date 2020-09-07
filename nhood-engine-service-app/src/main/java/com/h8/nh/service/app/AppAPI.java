package com.h8.nh.service.app;

import com.h8.nh.service.app.command.AddDataCommandHandlerAPI;
import com.h8.nh.service.app.query.FindClosestDataQueryHandlerAPI;

public interface AppAPI extends
        FindClosestDataQueryHandlerAPI,
        AddDataCommandHandlerAPI {
}
