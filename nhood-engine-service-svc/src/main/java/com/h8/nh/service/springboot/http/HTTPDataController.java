package com.h8.nh.service.springboot.http;

import com.h8.nh.service.dto.EngineDataDTO;
import com.h8.nh.service.port.webflux.AddDataRequestHandler;
import com.h8.nh.service.port.webflux.FindDataRequestHandler;
import com.h8.nh.service.port.webflux.WebFluxAPIException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.UUID;

@RestController
public class HTTPDataController {

    private final AddDataRequestHandler addDataRequestHandler;
    private final FindDataRequestHandler findDataRequestHandler;

    public HTTPDataController(
            AddDataRequestHandler addDataRequestHandler,
            FindDataRequestHandler findDataRequestHandler
    ) {
        this.addDataRequestHandler = addDataRequestHandler;
        this.findDataRequestHandler = findDataRequestHandler;
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addData(
            @RequestBody EngineDataDTO data
    ) throws WebFluxAPIException {
        var e = addDataRequestHandler.add(data).block();
        if (e == null) {
            return ResponseEntity.badRequest().body(null);
        }

        var eURL = URI.create("/data/" + e);
        return ResponseEntity.created(eURL).body(null);
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Flux<EngineDataDTO>> findClosestData(
            @RequestBody EngineDataDTO data,
            @RequestParam Integer size
    ) throws WebFluxAPIException {
        if (size <= 0) {
            throw new IllegalArgumentException("Requested data size must be positive");
        }
        var e = findDataRequestHandler.find(data, size);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception e) {
        if (e instanceof WebFluxAPIException) {
            var message = resolveFullExceptionMessage(e);
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (e instanceof IllegalArgumentException) {
            var message = resolveFullExceptionMessage(e);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String resolveFullExceptionMessage(Exception e) {
        Throwable t = e;

        var message = new StringBuilder();
        while (t != null) {
            if (message.length() != 0) {
                message.append(": ");
                message.append(System.lineSeparator());
            }
            message.append(t.getMessage());
            t = t.getCause();
        }

        return message.toString();
    }
}
