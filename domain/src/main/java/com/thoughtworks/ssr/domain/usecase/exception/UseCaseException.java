package com.thoughtworks.ssr.domain.usecase.exception;

import com.thoughtworks.ssr.common.exception.BaseException;
import com.thoughtworks.ssr.domain.user.exception.UserException;

public class UseCaseException extends BaseException {
    public UseCaseException(Type type, String message) {
        super(type, message);
    }

    public static UserException notFound() {
        return new UserException(Type.NOT_FOUND, "use_case_not_found");
    }

}
