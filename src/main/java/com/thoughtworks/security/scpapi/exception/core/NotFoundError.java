package com.thoughtworks.security.scpapi.exception.core;

public enum NotFoundError {

    NOT_FOUND_APPLICATION("Application not found"),
    NOT_FOUND_PROJECT("Project not found"),
    NOT_FOUND_SCAN_RESULT("Scan result not found"),
    NOT_FOUND_SCAN_TASK("Scan task not found"),
    NOT_FOUND_SECURITY_TOOL("Security tool not found"),
    NOT_FOUND_USE_CASE_GROUP("Use case group not found"),
    NOT_FOUND_USE_CASE("Use case not found");

    private final String message;

    NotFoundError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
