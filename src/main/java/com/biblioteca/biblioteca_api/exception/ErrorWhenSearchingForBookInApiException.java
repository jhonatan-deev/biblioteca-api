package com.biblioteca.biblioteca_api.exception;

public class ErrorWhenSearchingForBookInApiException extends RuntimeException {
    public ErrorWhenSearchingForBookInApiException(String message) {
        super(message);
    }
}
