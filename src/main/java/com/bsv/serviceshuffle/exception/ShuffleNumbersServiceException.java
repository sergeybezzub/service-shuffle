package com.bsv.serviceshuffle.exception;

public class ShuffleNumbersServiceException extends RuntimeException {

    private static final long serialVersionUID = -6711537351558648863L;

    public ShuffleNumbersServiceException(String message) {
        super(message);
    }

    public ShuffleNumbersServiceException(String message, Throwable exception) {
        super(message, exception);
    }
}
