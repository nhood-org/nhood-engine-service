package com.h8.nh.service.springboot.configuration;

import com.h8.nh.service.app.AppAPI;
import com.h8.nh.service.app.DefaultAppAPI;
import com.h8.nh.service.port.webflux.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AppAPI AppAPI() {
        return new DefaultAppAPI();
    }

    @Bean
    public AddDataRequestHandler AddDataRequestHandler(AppAPI app) {
        return new AddDataRequestHandlerWebFluxAdapter(app);
    }

    @Bean
    public FindDataRequestHandler FindDataRequestHandler(AppAPI app) {
        return new FindDataRequestHandlerWebFluxAdapter(app);
    }
}
