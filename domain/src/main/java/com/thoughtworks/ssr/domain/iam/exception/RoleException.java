package com.thoughtworks.ssr.domain.iam.exception;

import com.thoughtworks.ssr.common.exception.BaseException;

public class RoleException extends BaseException {

    public RoleException(Type type, String message) {
        super(type, message);
    }

    public static RoleException notFound() {
        return new RoleException(Type.NOT_FOUND, "role_not_found");
    }

}
