package com.thoughtworks.ssr.domain.user.exception;

import com.thoughtworks.ssr.common.exception.BaseException;

public class UserException extends BaseException {

    public UserException(Type type, String message) {
        super(type, message);
    }

    public static UserException emailConflicted() {
        return new UserException(Type.CONFLICT, "email_conflicted");
    }

    public static UserException usernameConflicted() {
        return new UserException(Type.CONFLICT, "username_conflicted");
    }

    public static UserException notFound() {
        return new UserException(Type.NOT_FOUND, "user_not_found");
    }

    public static UserException noPermissionUpdate() {
        return new UserException(Type.FORBIDDEN, "no_permission_update_user");
    }
}
