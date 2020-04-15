package com.h8.nh.service.app.svc.controller;

import com.h8.nh.service.app.svc.dto.ClosestDataDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class FindClosestDataController {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Mono<ClosestDataDTO>> findById(@PathVariable("id") Integer id) {
        Mono<ClosestDataDTO> e = Mono.just(new ClosestDataDTO(id));
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(e, status);
    }
}
