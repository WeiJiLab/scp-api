package com.thoughtworks.ssr.domain.securitytool.exception;

import com.thoughtworks.ssr.common.exception.BaseException;
import com.thoughtworks.ssr.domain.user.exception.UserException;

public class SecurityToolException extends BaseException {
    public SecurityToolException(Type type, String message) {
        super(type, message);
    }

    public static UserException notFound() {
        return new UserException(Type.NOT_FOUND, "security_tool_not_found");
    }

}
