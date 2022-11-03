package com.thoughtworks.ssr.domain.project.exception;

import com.thoughtworks.ssr.common.exception.BaseException;

public class AppInfoException extends BaseException {
    public AppInfoException(Type type, String message) {
        super(type, message);
    }

    public static AppInfoException notFound() {
        return new AppInfoException(Type.NOT_FOUND, "application_not_found");
    }
}
