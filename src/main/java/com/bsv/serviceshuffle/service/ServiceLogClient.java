package com.bsv.serviceshuffle.service;

import com.bsv.serviceshuffle.dto.ShuffleNumbersRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;


@Service
@Slf4j
public class ServiceLogClient {

    @Value("${servicelog.url}")
    String serviceLogURL;

    private HttpClient httpClient = HttpClients.createDefault();

    public void sendShuffledNumbersInfo(Integer requestedAmount, Set<Integer> recievedNumbers) {
        HttpPost httpPost = new HttpPost(serviceLogURL);
        try {
            if (recievedNumbers != null && !recievedNumbers.isEmpty()) {
                ShuffleNumbersRequestBody body = ShuffleNumbersRequestBody.builder()
                        .amount(requestedAmount)
                        .receivedSize(recievedNumbers.size())
                        .message("SUCCESS")
                        .build();
                String jsonOk = cteateJson(body);
                if(jsonOk != null) {
                    StringEntity entity = new StringEntity(jsonOk);
                    httpPost.setEntity(entity);
                }
                else {
                    log.error("Attempt to serialize object {} to JSON has failed!", body.toString());
                }
            } else {
                ShuffleNumbersRequestBody body = ShuffleNumbersRequestBody.builder()
                        .amount(requestedAmount)
                        .receivedSize(0)
                        .message("FAIL")
                        .build();
                String jsonNotOk = cteateJson(body);
                if(jsonNotOk != null) {
                    StringEntity entity = new StringEntity(jsonNotOk);
                    httpPost.setEntity(entity);
                } else {
                    log.error("Attempt to serialize object {} to JSON has failed!", body.toString());
                }
            }
        } catch(UnsupportedEncodingException e) {
            log.error("Attempt to create request to service-log has failed! {}",e.toString(), e);
        }
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try (CloseableHttpResponse response = ((CloseableHttpClient) httpClient).execute(httpPost)) {
            response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            log.error("Attemot to send request to service-log has failed! {}",e.toString(), e);
        }
    }


    public String getServiceLogURL() {
        return serviceLogURL;
    }

    private String cteateJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        try {
            return writer.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Object can't be sericalized to JSON string! {} ", e.getMessage(), e);
            return null;
        }
    }
}
