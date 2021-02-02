package com.thoughtworks.security.scpapi.exception;

public class DuplicatedApplicationException extends DuplicatedException {

    private static final String MESSAGE = "Application existed";

    public DuplicatedApplicationException() {
        super(MESSAGE);
    }
}
