package com.thoughtworks.ssr.iam.auth.business.usecases;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRegisterCase {

    @Getter
    @Setter
    public static class Request {
        @NotBlank(message = "username_required")
        private String username;

        @NotBlank(message = "email_required")
        private String email;

        @NotNull(message = "password_required")
        private String password;
    }
}
