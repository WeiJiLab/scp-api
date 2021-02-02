package com.thoughtworks.security.scpapi.exception;

public class DuplicatedSecurityToolException extends DuplicatedException {

    private static final String MESSAGE = "Security tool existed";

    public DuplicatedSecurityToolException() {
        super(MESSAGE);
    }
}
