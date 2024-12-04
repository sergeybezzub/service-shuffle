package com.aiomed.treatmentservice.service;

import com.bsv.serviceshuffle.ServiceShuffleApplication;
import com.bsv.serviceshuffle.exception.ShuffleNumbersServiceException;
import com.bsv.serviceshuffle.service.ShuffledNumbersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

@SpringBootTest
@ContextConfiguration(classes = ServiceShuffleApplication.class)
public class TaskSchedulerServerTest {

    @Autowired
    ShuffledNumbersService service;


    @Test
    public void createShuffledNumbersPositive(){

        Set<Integer> numbers = service.createShuffledNumbers(10);
        Assertions.assertTrue(numbers.size() == 10);

    }

    @Test
    public void createShuffledNumbersNegative(){

        Assertions.assertThrows(ShuffleNumbersServiceException.class, () -> {
            service.createShuffledNumbers(0); // Invalid amount is passed
        });

    }

}
