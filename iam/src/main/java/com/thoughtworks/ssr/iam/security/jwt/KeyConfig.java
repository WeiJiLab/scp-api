package com.thoughtworks.ssr.iam.security.jwt;

import com.thoughtworks.ssr.iam.config.AppJwtProperties;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
@RequiredArgsConstructor
public class KeyConfig {

    private final AppJwtProperties appJwtProperties;

    @Bean
    public Key key() {

        byte[] keyBytes = Decoders.BASE64.decode(appJwtProperties.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
