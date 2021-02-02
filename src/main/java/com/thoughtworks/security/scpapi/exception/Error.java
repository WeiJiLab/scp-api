package com.thoughtworks.security.scpapi.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Error {

    private String message;
    private LocalDateTime dateTime;
}
