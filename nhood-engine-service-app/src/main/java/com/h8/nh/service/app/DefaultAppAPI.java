package com.h8.nh.service.app;

import com.h8.nh.nhoodengine.matrix.impl.DataMatrixCellBasedRepository;
import com.h8.nh.service.app.command.AddDataCommand;
import com.h8.nh.service.app.command.AddDataCommandHandler;
import com.h8.nh.service.app.command.AddDataCommandHandlerException;
import com.h8.nh.service.app.command.AddDataCommandResult;
import com.h8.nh.service.app.engine.EngineData;
import com.h8.nh.service.app.engine.adapter.EngineDataFinderImpl;
import com.h8.nh.service.app.engine.adapter.EngineDataRepositoryImpl;
import com.h8.nh.service.app.engine.adapter.EngineDataResourceKey;
import com.h8.nh.service.app.query.FindClosestDataQuery;
import com.h8.nh.service.app.query.FindClosestDataQueryHandler;
import com.h8.nh.service.app.query.FindClosestDataQueryHandlerException;
import com.h8.nh.service.app.query.FindClosestDataQueryResult;

public class DefaultAppAPI implements AppAPI {

    private final AddDataCommandHandler addDataCommandHandler;
    private final FindClosestDataQueryHandler findClosestDataQueryHandler;

    public DefaultAppAPI() {
        var config = AppAPIConfig.loadFromEnv();

        var nHoodRepository = new DataMatrixCellBasedRepository<EngineDataResourceKey, EngineData>(config.getMetadataVectorSize());

        var engineRepository = new EngineDataRepositoryImpl(nHoodRepository);
        this.addDataCommandHandler = new AddDataCommandHandler(engineRepository);

        var engineFinder = new EngineDataFinderImpl(nHoodRepository);
        this.findClosestDataQueryHandler = new FindClosestDataQueryHandler(engineFinder);
    }

    @Override
    public AddDataCommandResult handle(AddDataCommand command)
            throws AddDataCommandHandlerException {
        return addDataCommandHandler.handle(command);
    }

    @Override
    public FindClosestDataQueryResult handle(FindClosestDataQuery query)
            throws FindClosestDataQueryHandlerException {
        return findClosestDataQueryHandler.handle(query);
    }
}
