package com.bsv.serviceshuffle.service;

import com.bsv.serviceshuffle.exception.ShuffleNumbersServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ShuffledNumbersService {

    @Autowired
    private ServiceLogClient serviceLogClient;

    private static int MAX_DESIRED_VALUE = 1000;

    public Set<Integer> createShuffledNumbers(Integer amount) {

        if(amount == null || amount < 1) {
            throw new ShuffleNumbersServiceException("The amount of shuffled numbers should be > 0");
        }

        Set<Integer> shuffledNumbers = new LinkedHashSet<>();

        do {
            Random random = new Random();
            // According to AMOUNT parameter will be Generated pseudorandom integers with values from 1 to MAX_POSIBLE_VALUE
            Integer nextValue = random.nextInt(MAX_DESIRED_VALUE)+1;
            shuffledNumbers.add(nextValue);
        } while(shuffledNumbers.size() < amount);

        // Sending request to service-log asynchronously
        Runnable asyncRequest = () -> {
            serviceLogClient.sendShuffledNumbersInfo(amount, shuffledNumbers);
        };
        Thread thread = new Thread(asyncRequest);
        thread.start();

        return shuffledNumbers;

    }


}
