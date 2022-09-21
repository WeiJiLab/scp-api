package com.thoughtworks.ssr.common.exception;

public class ScpException extends RuntimeException {

    private String message;

    public ScpException() {
    }

    public ScpException(String message) {
        super(message);
        this.message = message;
    }

}
