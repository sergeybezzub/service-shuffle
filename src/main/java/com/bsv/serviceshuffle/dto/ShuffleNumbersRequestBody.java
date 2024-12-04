package com.bsv.serviceshuffle.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ShuffleNumbersRequestBody {
    Integer amount;
    Integer receivedSize;
    String message;

    public Integer getAmount() {
        return amount;
    }

    public Integer getReceivedSize() {
        return receivedSize;
    }

    public String getMessage() {
        return message;
    }

}
