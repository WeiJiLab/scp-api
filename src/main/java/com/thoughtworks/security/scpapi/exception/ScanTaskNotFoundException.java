package com.thoughtworks.security.scpapi.exception;

public class ScanTaskNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Scan task not found";

    public ScanTaskNotFoundException() {
        super(MESSAGE);
    }
}
