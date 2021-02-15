package com.thoughtworks.security.scpapi.util;

import com.thoughtworks.security.scpapi.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class JwtUtil {
    public static Optional<String> generateToken(User user, Map<String, Object> claims, String secret, String prefix) {
        if (user == null) {
            return Optional.empty();
        }

        // TODO: this item should be assigned by parameter later
        if (claims == null) {
            claims = new HashMap<>();
        }

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(user.getUid()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return Optional.of(String.format("%s %s", prefix, jwt));
    }

    public static String getUsername(String token, String secret) {
        return getClaimFromToken(token, secret, Claims::getSubject);
    }

    public static Boolean isTokenExpired(String token, String secret) {
        final Date expiration = getClaimFromToken(token, secret, Claims::getExpiration);
        if (expiration == null) {
            return false;
        }
        return expiration.before(new Date());
    }

    private static <T> T getClaimFromToken(String token, String secret, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token, secret);
        return claimsResolver.apply(claims);
    }

    private static Claims getAllClaimsFromToken(String token, String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
