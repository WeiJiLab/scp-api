package com.thoughtworks.security.scpapi.exception;

public class UseCaseNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Use case not found";

    public UseCaseNotFoundException() {
        super(MESSAGE);
    }
}
