package com.thoughtworks.security.scpapi.controller;

import com.thoughtworks.security.scpapi.config.properties.JWTProperties;
import com.thoughtworks.security.scpapi.service.UserService;
import com.thoughtworks.security.scpapi.util.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/saml-callback")
public class SamlCallbackHandler {
    private final JWTProperties jwtProperties;
    private final UserService userService;

    private static final ResponseEntity<String> CALLBACK_FAILURE_RESPONSE = ResponseEntity
            .status(HttpStatus.FOUND)
            .header("Location", "https://opendia.security.thoughtworks.cn")
            .body("?err='failed to accept saml'");

    @PostMapping
    public ResponseEntity<String> callback(@RequestBody String samlResponse) {
        return userService.save(samlResponse).map(user -> {
            Map<String, Object> claim = new HashMap<>();
            claim.put("userid", user.getUid());
            claim.put("username", user.getUsername());
            return JwtUtil.generateToken(user, claim, this.jwtProperties.getSecret(), "Bearer").map(token ->
                    ResponseEntity
                            .status(HttpStatus.FOUND)
                            .header("Location", String.format("https://scp.security.thoughtworks.cn/?token=%s", token))
                            .body("")
            ).orElse(CALLBACK_FAILURE_RESPONSE);
        }).orElse(CALLBACK_FAILURE_RESPONSE);
    }
}
