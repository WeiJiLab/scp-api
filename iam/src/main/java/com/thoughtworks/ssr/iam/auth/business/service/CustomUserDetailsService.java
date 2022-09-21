package com.thoughtworks.ssr.iam.auth.business.service;

import com.thoughtworks.ssr.domain.user.model.CustomUserDetails;
import com.thoughtworks.ssr.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var dbUser = userService.findByUsername(username);
        log.info("Fetched user : " + dbUser + " by " + username);
        return Optional.of(dbUser)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("该用户不存在"));
    }

    public UserDetails loadUserById(Long id) {
        var dbUser = userService.findById(id);
        log.info("Fetched user : " + dbUser + " by " + id);
        return Optional.of(dbUser)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("该用户不存在"));
    }

    public UserDetails loadUserByEmail(String email) {
        var dbUser = userService.findByEmail(email);
        log.info("Fetched user : " + dbUser + " by " + email);
        return Optional.of(dbUser)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("该用户不存在"));
    }

}
