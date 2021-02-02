package com.thoughtworks.security.scpapi.exception;

public class ScanResultNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Scan task not found";

    public ScanResultNotFoundException() {
        super(MESSAGE);
    }
}
