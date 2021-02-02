package com.thoughtworks.security.scpapi.exception;

public class DuplicatedProjectException extends DuplicatedException {

    private static final String MESSAGE = "Project existed";

    public DuplicatedProjectException() {
        super(MESSAGE);
    }
}
