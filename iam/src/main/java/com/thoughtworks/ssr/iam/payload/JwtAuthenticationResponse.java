package com.thoughtworks.ssr.iam.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class JwtAuthenticationResponse {
    private final OffsetDateTime timestamp = OffsetDateTime.now();
    private String username;
    private String email;
    private String accessToken;
    private String tokenType;
    private List<String> roles;
    private Long expiryDuration;
}

