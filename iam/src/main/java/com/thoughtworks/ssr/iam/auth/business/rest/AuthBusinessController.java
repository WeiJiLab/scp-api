package com.thoughtworks.ssr.iam.auth.business.rest;

import com.thoughtworks.ssr.domain.iam.model.CustomUserDetails;
import com.thoughtworks.ssr.iam.auth.business.exception.AuthBusinessException;
import com.thoughtworks.ssr.iam.auth.business.service.AuthBusinessService;
import com.thoughtworks.ssr.iam.auth.business.usecases.UserLoginCase;
import com.thoughtworks.ssr.iam.auth.business.usecases.UserRegisterCase;
import com.thoughtworks.ssr.iam.config.AppJwtProperties;
import com.thoughtworks.ssr.common.payload.ApiResponse;
import com.thoughtworks.ssr.iam.payload.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.stream.Collectors;

/**
 * 认证相关接口
 */
@RestController
@RequestMapping("/api/business/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthBusinessController {
    private final AuthBusinessService authBusinessService;
    private final AppJwtProperties appJwtProperties;

    @GetMapping("/checkEmailInUse")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse checkEmailInUse(@RequestParam("email") String email) {
        var emailExists = authBusinessService.emailAlreadyExists(email);
        return ApiResponse.success(emailExists.toString());
    }

    @GetMapping("/checkUsernameInUse")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse checkUsernameInUse(@RequestParam(
            "username") String username
    ) {
        var usernameExists = authBusinessService.usernameAlreadyExists(username);
        return ApiResponse.success(usernameExists.toString());
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse registerUser(@Valid @RequestBody UserRegisterCase.Request request) {
        var user = authBusinessService.registerUser(request);

        return ApiResponse.success(
                String.format("User %s registered successfully.", user.getUsername())
        );
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthenticationResponse authenticateUser(@Valid @RequestBody UserLoginCase.Request request) {
        var authentication = authBusinessService.authenticateEmailAndPass(request)
                .orElseThrow(AuthBusinessException::couldNotLoginUser);

        var customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        log.info("Logged in User returned [API]: " + customUserDetails.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var roles = customUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        var jwtToken = authBusinessService.generateToken(customUserDetails);

        return JwtAuthenticationResponse.builder()
                .username(customUserDetails.getUsername())
                .email(customUserDetails.getEmail())
                .accessToken(jwtToken)
                .expiryDuration(appJwtProperties.getExpirationMs())
                .tokenType(appJwtProperties.getHeaderPrefix())
                .roles(roles)
                .build();
    }


}
