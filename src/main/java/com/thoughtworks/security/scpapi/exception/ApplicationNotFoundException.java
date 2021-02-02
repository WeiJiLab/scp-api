package com.thoughtworks.security.scpapi.exception;

public class ApplicationNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Application not found";

    public ApplicationNotFoundException() {
        super(MESSAGE);
    }
}
