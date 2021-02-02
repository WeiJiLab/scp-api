package com.thoughtworks.security.scpapi.exception;

public class UseCaseGroupNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Use case group not found";

    public UseCaseGroupNotFoundException() {
        super(MESSAGE);
    }
}
