package com.h8.nh.service.springboot.controller;

import com.h8.nh.service.port.webflux.ClosestDataDTO;
import com.h8.nh.service.port.webflux.ClosestDataFinder;
import com.h8.nh.service.port.webflux.ClosestDataFinderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class FindClosestDataController {

    private ClosestDataFinder finder;

    public FindClosestDataController(ClosestDataFinder finder) {
        this.finder = finder;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Mono<ClosestDataDTO>> findById(@PathVariable("id") Integer id) {
        try {
            var e = finder.findByID(id);
            return new ResponseEntity<>(e, HttpStatus.OK);
        } catch (ClosestDataFinderException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
