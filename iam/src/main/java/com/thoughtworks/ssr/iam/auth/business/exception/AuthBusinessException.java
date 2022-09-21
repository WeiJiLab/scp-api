package com.thoughtworks.ssr.iam.auth.business.exception;

import com.thoughtworks.ssr.iam.exception.BaseAuthException;

public class AuthBusinessException extends BaseAuthException {
    public AuthBusinessException(String message) {
        super(message);
    }

    public static AuthBusinessException emailAlreadyExists() {
        return new AuthBusinessException("email_already_exists");
    }

    public static AuthBusinessException usernameAlreadyExists() {
        return new AuthBusinessException("username_already_exists");
    }

    public static AuthBusinessException missingUserObjectInDatabase() {
        return new AuthBusinessException("missing_user_object_in_database");
    }

    public static AuthBusinessException couldNotLoginUser() {
        return new AuthBusinessException("could_not_login_user");
    }

}
