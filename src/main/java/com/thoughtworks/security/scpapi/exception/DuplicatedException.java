package com.thoughtworks.security.scpapi.exception;

public class DuplicatedException extends RuntimeException {

    public DuplicatedException(String message) {
        super(message);
    }
}
