package com.h8.nh.service.springboot.configuration;

import com.h8.nh.service.port.webflux.ClosestDataFinder;
import com.h8.nh.service.port.webflux.ClosestDataFinderWebFluxAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ClosestDataFinder ClosestDataFinder() {
        return new ClosestDataFinderWebFluxAdapter();
    }
}
