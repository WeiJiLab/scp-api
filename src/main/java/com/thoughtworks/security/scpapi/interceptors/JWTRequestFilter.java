package com.thoughtworks.security.scpapi.interceptors;

import com.thoughtworks.security.scpapi.config.properties.JWTProperties;
import com.thoughtworks.security.scpapi.entity.User;
import com.thoughtworks.security.scpapi.service.impl.UserServiceImpl;
import com.thoughtworks.security.scpapi.util.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {
    private final JWTProperties jwtProperties;
    private final UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        String token = null;
        String username = null;

        if (authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            username = JwtUtil.getUsername(token, jwtProperties.getSecret());
        }

        if (username != null) {
            if (!JwtUtil.isTokenExpired(token, jwtProperties.getSecret())) {
                User user = userService.findByName(username).orElse(null);

                // TODO: need implement authorization here with idp to get Authorities

                RunAsUserToken upat = new RunAsUserToken(String.valueOf(user.getUid()), user, null, null, null);
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            } else {
                userService.delete(username);
            }
        }

        filterChain.doFilter(request, response);
    }
}
