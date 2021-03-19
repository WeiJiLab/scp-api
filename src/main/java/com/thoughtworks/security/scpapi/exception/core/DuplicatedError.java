package com.thoughtworks.security.scpapi.exception.core;

public enum DuplicatedError {
    EXISTED_PROJECT("Project existed"),
    EXISTED_APPLICATION("Application existed"),
    EXISTED_SECURITY_TOOL("Security tool existed"),
    EXISTED_USE_CASE_GROUP("Use case group existed");

    private final String message;

    DuplicatedError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
