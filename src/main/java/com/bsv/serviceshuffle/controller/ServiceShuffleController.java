package com.bsv.serviceshuffle.controller;

import com.bsv.serviceshuffle.service.ServiceLogClient;
import com.bsv.serviceshuffle.service.ShuffledNumbersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/service-shuffle/")
@Slf4j
public class ServiceShuffleController {

    @Autowired
    private ShuffledNumbersService shuffledNumbersService;

    @PostMapping("/{amount}")
    public ResponseEntity<Set<Integer>> createShuffledNumbers(@PathVariable Integer amount) {
        final Set<Integer> shuffledNumbers = shuffledNumbersService.createShuffledNumbers(amount);
        return ResponseEntity.ok(shuffledNumbers);
    }

}
