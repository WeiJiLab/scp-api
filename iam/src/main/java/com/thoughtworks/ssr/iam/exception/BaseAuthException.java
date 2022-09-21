package com.thoughtworks.ssr.iam.exception;

import com.thoughtworks.ssr.common.exception.ScpException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseAuthException extends ScpException {
    private String message;

    public BaseAuthException(String message) {
        this.message = message;
    }
}
