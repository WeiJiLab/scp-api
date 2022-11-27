package com.thoughtworks.ssr.iam.auth.business.usecases;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserLoginCase {
    @Getter
    @Setter
    public static class Request {
        @NotBlank(message = "email_required")
        private String email;

        @NotNull(message = "password_required")
        private String password;
    }
}
