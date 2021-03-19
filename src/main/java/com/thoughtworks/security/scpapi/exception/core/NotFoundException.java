package com.thoughtworks.security.scpapi.exception.core;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(NotFoundError notFoundError) {
        super(notFoundError.getMessage());
    }
}
