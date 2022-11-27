package com.thoughtworks.ssr.iam.security.jwt;

import com.thoughtworks.ssr.iam.auth.admin.service.CustomAdminDetailsService;
import com.thoughtworks.ssr.iam.auth.business.service.CustomUserDetailsService;
import com.thoughtworks.ssr.iam.config.AppJwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AppJwtProperties appJwtProperties;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAdminDetailsService customAdminDetailsService;

    /**
     * Filter the incoming request for a valid token in the request header
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && jwtTokenValidator.validateToken(jwt)) {
                setAuthentication(request, jwt);
            }
        } catch (Exception ex) {
            log.error("Failed to set user authentication in security context: ", ex);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(HttpServletRequest request, String jwt) {
        getUserDetails(jwt).ifPresent(userDetails -> {
            var authorities = jwtTokenProvider.getAuthoritiesFromJWT(jwt);
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, jwt, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }

    private Optional<?> getUserDetails(String jwt) {

        var accountType = jwtTokenProvider.getAccountTypeWithJWT(jwt);
        var userId = jwtTokenProvider.getUserIdFromJWT(jwt);

        return switch (accountType) {
            case ADMIN -> Optional.ofNullable(customAdminDetailsService.loadAdminById(userId));
            case USER -> Optional.ofNullable(customUserDetailsService.loadUserById(userId));
            default -> Optional.empty();
        };
    }

    /**
     * Extract the token from the Authorization request header
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(appJwtProperties.getHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(appJwtProperties.getHeaderPrefix())) {
            log.info("Extracted Token: " + bearerToken);
            return bearerToken.replace(appJwtProperties.getHeaderPrefix(), "");
        }
        return null;
    }
}
