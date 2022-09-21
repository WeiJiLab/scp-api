package com.thoughtworks.ssr.iam.auth.admin.service;

import com.thoughtworks.ssr.domain.admin.model.CustomAdminDetails;
import com.thoughtworks.ssr.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomAdminDetailsService implements UserDetailsService {

    private final AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var dbUser = adminService.findByUsername(username);
        log.info("Fetched user : " + dbUser + " by " + username);
        return dbUser.map(CustomAdminDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("该用户不存在"));
    }

    public UserDetails loadAdminById(Long id) {
        var dbUser = adminService.findByIdAndActiveIsTrue(id);
        log.info("Fetched user : " + dbUser + " by " + id);
        return dbUser.map(CustomAdminDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("该用户不存在"));
    }

    public UserDetails loadUserByPhoneNumber(String phoneNumber) {
        var dbUser = adminService.findByPhoneNumber(phoneNumber);
        log.info("Fetched user : " + dbUser + " by phoneNumber" + phoneNumber);
        return dbUser.map(CustomAdminDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("该用户不存在"));
    }

    public CustomAdminDetails getUserByPhoneNumber(String phoneNumber) {
        var dbUser = adminService.findByPhoneNumber(phoneNumber);
        log.info("Fetched user : " + dbUser + " by phoneNumber" + phoneNumber);
        return dbUser.map(CustomAdminDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("该用户不存在"));
    }

}
