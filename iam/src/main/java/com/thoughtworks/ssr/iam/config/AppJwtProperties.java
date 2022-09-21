package com.thoughtworks.ssr.iam.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.jwt")
@Slf4j
public class AppJwtProperties {
    private long expirationMs = 864000000;
    private String header = "Authorization";
    private String headerPrefix = "Bearer ";
    private String secret = "";
}
