package com.aiomed.treatmentservice.service;

import com.bsv.serviceshuffle.ServiceShuffleApplication;
import com.bsv.serviceshuffle.service.ServiceLogClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

@SpringBootTest
@ContextConfiguration(classes = ServiceShuffleApplication.class)

public class ServiceLogClientTest {

        @Autowired
        ServiceLogClient client;

        @Test
        public void testUrlConfiguration(){

            String url = client.getServiceLogURL();
            Assertions.assertTrue(url != null);
            Assertions.assertTrue(url.contains("localhost"));

        }

    }
