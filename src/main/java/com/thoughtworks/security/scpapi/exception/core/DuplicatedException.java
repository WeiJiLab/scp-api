package com.thoughtworks.security.scpapi.exception.core;

import com.thoughtworks.security.scpapi.exception.ScpException;

public class DuplicatedException extends ScpException {

    public DuplicatedException(String message) {
        super(message);
    }

    public DuplicatedException(DuplicatedError duplicatedError) {
        super(duplicatedError.getMessage());
    }
}
