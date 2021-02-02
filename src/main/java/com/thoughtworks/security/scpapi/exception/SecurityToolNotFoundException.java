package com.thoughtworks.security.scpapi.exception;

public class SecurityToolNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Security tool not found";

    public SecurityToolNotFoundException() {
        super(MESSAGE);
    }
}
