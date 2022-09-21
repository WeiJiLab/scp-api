package com.thoughtworks.ssr.iam.exception;

import com.thoughtworks.ssr.common.exception.ScpException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
@Getter
public class InvalidTokenRequestException extends ScpException {

    private final String tokenType;
    private final String token;
    private final String message;

    public InvalidTokenRequestException(String tokenType, String token, String message) {
        super(String.format("%s: [%s] token: [%s] ", message, tokenType, token));
        this.tokenType = tokenType;
        this.token = token;
        this.message = message;
    }
}
