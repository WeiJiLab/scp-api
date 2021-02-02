package com.thoughtworks.security.scpapi.exception;

public class DuplicatedUseCaseGroupException extends DuplicatedException {

    private static final String MESSAGE = "Use case group existed";

    public DuplicatedUseCaseGroupException() {
        super(MESSAGE);
    }
}
