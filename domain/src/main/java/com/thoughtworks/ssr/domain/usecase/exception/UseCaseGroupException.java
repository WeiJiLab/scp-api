package com.thoughtworks.ssr.domain.usecase.exception;

import com.thoughtworks.ssr.common.exception.BaseException;
import com.thoughtworks.ssr.domain.iam.exception.UserException;

public class UseCaseGroupException extends BaseException {
    public UseCaseGroupException(Type type, String message) {
        super(type, message);
    }

    public static UserException notFound() {
        return new UserException(Type.NOT_FOUND, "use_case_group_not_found");
    }

}
