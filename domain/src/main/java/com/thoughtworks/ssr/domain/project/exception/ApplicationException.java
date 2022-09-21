package com.thoughtworks.ssr.domain.project.exception;

import com.thoughtworks.ssr.common.exception.BaseException;

public class ApplicationException extends BaseException {
    public ApplicationException(Type type, String message) {
        super(type, message);
    }

    public static ApplicationException notFound() {
        return new ApplicationException(Type.NOT_FOUND, "application_not_found");
    }
}
