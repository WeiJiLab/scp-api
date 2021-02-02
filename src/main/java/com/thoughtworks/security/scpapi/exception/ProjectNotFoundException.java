package com.thoughtworks.security.scpapi.exception;

public class ProjectNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Project not found";

    public ProjectNotFoundException() {
        super(MESSAGE);
    }
}
