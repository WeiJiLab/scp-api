package com.thoughtworks.ssr.iam.auth.business.service;

import com.thoughtworks.ssr.domain.role.service.RoleService;
import com.thoughtworks.ssr.domain.user.model.CustomUserDetails;
import com.thoughtworks.ssr.domain.user.model.User;
import com.thoughtworks.ssr.domain.user.service.UserService;
import com.thoughtworks.ssr.iam.auth.business.authentication.UserEmailAuthenticationToken;
import com.thoughtworks.ssr.iam.auth.business.exception.AuthBusinessException;
import com.thoughtworks.ssr.iam.auth.business.usecases.UserLoginCase;
import com.thoughtworks.ssr.iam.auth.business.usecases.UserRegisterCase;
import com.thoughtworks.ssr.iam.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthBusinessService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public Boolean emailAlreadyExists(String email) {
        return userService.existsByEmail(email);
    }

    public Boolean usernameAlreadyExists(String username) {
        return userService.existsByUsername(username);
    }

    public User registerUser(UserRegisterCase.Request request) {
        var email = request.getEmail();

        if (emailAlreadyExists(email)) {
            log.error("email already exists: " + email);
            throw AuthBusinessException.emailAlreadyExists();
        }

        var newUser = createUser(request.getEmail(), request.getUsername(), request.getPassword());
        return userService.saveUser(newUser);
    }

    public User createUser(String email, String username, String password) {

        return User.builder()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(roleService.buildRoleWithUser())
                .isEmailVerified(false)
                .active(true)
                .build();
    }

    public Optional<Authentication> authenticateEmailAndPass(UserLoginCase.Request request) {

        var authenticate = authenticationManager.authenticate(
                new UserEmailAuthenticationToken(request.getEmail(), request.getPassword())
        );

        return Optional.ofNullable(authenticate);

    }

    /**
     * Generates a JWT token for the validated client
     */
    public String generateToken(CustomUserDetails customUserDetails) {
        return tokenProvider.generateToken(customUserDetails);
    }
}
