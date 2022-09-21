package com.thoughtworks.ssr.iam.security.jwt;

import com.thoughtworks.ssr.domain.admin.model.CustomAdminDetails;
import com.thoughtworks.ssr.domain.user.model.CustomUserDetails;
import com.thoughtworks.ssr.iam.config.AppJwtProperties;
import com.thoughtworks.ssr.iam.security.model.AccountType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.thoughtworks.ssr.iam.security.model.AccountType.ADMIN;
import static com.thoughtworks.ssr.iam.security.model.AccountType.USER;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final AppJwtProperties appJwtProperties;
    private static final String AUTHORITIES_CLAIM = "authorities";
    private static final String ACCOUNT_TYPE = "account_type";

    public String generateTokenFromUserId(Long userId) {
        var expiryDate = Instant.now().plusMillis(appJwtProperties.getExpirationMs());
        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.HS512, appJwtProperties.getSecret())
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appJwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public Date getTokenExpiryFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appJwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public long getExpiryDuration() {
        return appJwtProperties.getExpirationMs();
    }

    /**
     * Return the jwt authorities claim encapsulated within the token
     */
    public List<GrantedAuthority> getAuthoritiesFromJWT(String token) {
        var claims = Jwts.parser()
                .setSigningKey(appJwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();

        String authorities = claims.get(AUTHORITIES_CLAIM).toString();

        if (ObjectUtils.isEmpty(authorities)) {
            return new ArrayList<>();
        }

        return Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public AccountType getAccountTypeWithJWT(String token) {
        var claims = Jwts.parser()
                .setSigningKey(appJwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return AccountType.getAccountType(claims.get(ACCOUNT_TYPE).toString());
    }

    public String generateToken(CustomUserDetails customUserDetails) {
        var expiryDate = Instant.now().plusMillis(appJwtProperties.getExpirationMs());
        var authorities = getAuthorities(customUserDetails.getAuthorities());

        Map<String, Object> claims = new HashMap<>();
        claims.put(ACCOUNT_TYPE, USER);
        claims.put("username", customUserDetails.getUsername());

        return Jwts.builder()
                .setSubject(Long.toString(customUserDetails.getId()))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.HS512, appJwtProperties.getSecret())
                .claim(AUTHORITIES_CLAIM, authorities)
                .addClaims(claims)
                .compact();
    }


    private String getAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    public String generateToken(CustomAdminDetails customAdminDetails) {
        var expiryDate = Instant.now().plusMillis(appJwtProperties.getExpirationMs());
        var authorities = getAuthorities(customAdminDetails.getAuthorities());

        Map<String, Object> claims = new HashMap<>();
        claims.put(ACCOUNT_TYPE, ADMIN);

        return Jwts.builder()
                .setSubject(Long.toString(customAdminDetails.getId()))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.HS512, appJwtProperties.getSecret())
                .addClaims(claims)
                .claim(AUTHORITIES_CLAIM, authorities)
                .compact();
    }

}
