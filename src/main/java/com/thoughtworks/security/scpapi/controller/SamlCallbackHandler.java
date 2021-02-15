package com.thoughtworks.security.scpapi.controller;

import com.thoughtworks.security.scpapi.config.properties.JWTProperties;
import com.thoughtworks.security.scpapi.service.UserService;
import com.thoughtworks.security.scpapi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/saml-callback")
public class SamlCallbackHandler {
    private final JWTProperties jwtProperties;
    private final UserService userService;

    @GetMapping
    public Optional<String> callback(@RequestParam(name = "saml_response") String samlResponse) {
        return JwtUtil.generateToken(userService.save(samlResponse).orElse(null), null, this.jwtProperties.getSecret(), "Bearer");
    }
}
