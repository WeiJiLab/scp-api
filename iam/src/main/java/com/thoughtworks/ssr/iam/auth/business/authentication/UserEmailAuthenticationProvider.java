package com.thoughtworks.ssr.iam.auth.business.authentication;

import com.thoughtworks.ssr.iam.auth.business.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEmailAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //过滤器传过来的SmsAuthenticationToken
        UserEmailAuthenticationToken authenticationToken = (UserEmailAuthenticationToken) authentication;

        String email = (String) authenticationToken.getPrincipal();
        String password = (String) authenticationToken.getCredentials();

        var userDetails = customUserDetailsService.loadUserByEmail(email);

        if (!userDetails.isAccountNonLocked()) {
            throw new BadCredentialsException("该用户被禁用");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("用户名或密码错误");
        }

        var token = new UserEmailAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        // 设置用户的其他的详细信息(登录一些)
        token.setDetails(authenticationToken.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 判断类型是否一致
        return authentication.isAssignableFrom(UserEmailAuthenticationToken.class);
    }
}