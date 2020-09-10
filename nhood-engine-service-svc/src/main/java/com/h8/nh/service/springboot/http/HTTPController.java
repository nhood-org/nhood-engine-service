package com.h8.nh.service.springboot.http;

import com.h8.nh.service.port.webflux.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HTTPController {

    private final ClosestDataFinder finder;

    private final AddDataRequestHandler addDataRequestHandler;

    private final FindDataRequestHandler findDataRequestHandler;

    public HTTPController(
            ClosestDataFinder finder,
            AddDataRequestHandler addDataRequestHandler,
            FindDataRequestHandler findDataRequestHandler
    ) {
        this.finder = finder;
        this.addDataRequestHandler = addDataRequestHandler;
        this.findDataRequestHandler = findDataRequestHandler;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Mono<ClosestData>> findById(
            @PathVariable("id") Integer id
    ) {
        try {
            var e = finder.findByID(id);
            return new ResponseEntity<>(e, HttpStatus.OK);
        } catch (ClosestDataFinderException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Mono<EngineDataDTO>> addData(
            @RequestBody EngineDataDTO data
    ) throws WebFluxAPIException {
        var e = addDataRequestHandler.add(data);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Flux<EngineDataDTO>> findClosestData(
            @RequestBody EngineDataDTO data,
            @RequestParam Integer size
    ) throws WebFluxAPIException, WebFluxAPIBadRequestException {
        var e = findDataRequestHandler.find(data, size);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<String> handleException(Exception e) {
        if (e instanceof WebFluxAPIBadRequestException) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
