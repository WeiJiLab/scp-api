package com.thoughtworks.ssr.iam.security.jwt;

import com.thoughtworks.ssr.domain.admin.model.CustomAdminDetails;
import com.thoughtworks.ssr.domain.user.model.CustomUserDetails;
import com.thoughtworks.ssr.iam.config.AppJwtProperties;
import com.thoughtworks.ssr.iam.security.model.AccountType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.security.Key;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.thoughtworks.ssr.iam.security.model.AccountType.ADMIN;
import static com.thoughtworks.ssr.iam.security.model.AccountType.USER;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final Key key;

    private final AppJwtProperties appJwtProperties;
    private static final String AUTHORITIES_CLAIM = "authorities";
    private static final String ACCOUNT_TYPE = "account_type";

    public Long getUserIdFromJWT(String token) {
        return getClaimFromToken(token, claims -> Long.parseLong(claims.getSubject()));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * Return the jwt authorities claim encapsulated within the token
     */
    public List<GrantedAuthority> getAuthoritiesFromJWT(String token) {
        var authorities = getClaimFromToken(token, (claims) -> claims.get(AUTHORITIES_CLAIM).toString());

        if (ObjectUtils.isEmpty(authorities)) {
            return new ArrayList<>();
        }

        return Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public AccountType getAccountTypeWithJWT(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return AccountType.getAccountType(claims.get(ACCOUNT_TYPE).toString());
    }

    private String getAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    public String generateUserToken(CustomUserDetails customUserDetails) {

        var authorities = getAuthorities(customUserDetails.getAuthorities());
        var userId = customUserDetails.getId();

        var claims = new HashMap<String, Object>();
        claims.put(ACCOUNT_TYPE, USER);
        claims.put("username", customUserDetails.getUsername());

        return buildJwtToken(authorities, userId, claims);
    }

    public String generateAdminToken(CustomAdminDetails customAdminDetails) {
        var authorities = getAuthorities(customAdminDetails.getAuthorities());
        var adminId = customAdminDetails.getId();
        var claims = new HashMap<String, Object>();
        claims.put(ACCOUNT_TYPE, ADMIN);

        return buildJwtToken(authorities, adminId, claims);
    }

    private String buildJwtToken(String authorities, Long adminId, Map<String, Object> claims) {
        var expiryDate = Instant.now().plusMillis(appJwtProperties.getExpirationMs());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(Long.toString(adminId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(expiryDate))
                .claim(AUTHORITIES_CLAIM, authorities)
                .signWith(key)
                .compact();
    }

}
