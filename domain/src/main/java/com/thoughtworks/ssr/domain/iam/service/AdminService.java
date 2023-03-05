package com.thoughtworks.ssr.domain.iam.service;

import com.thoughtworks.ssr.domain.iam.model.Admin;
import com.thoughtworks.ssr.domain.iam.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public Optional<Admin> findByIdAndActiveIsTrue(Long id) {
        return adminRepository.findByIdAndActiveIsTrue(id);
    }

    public Boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin findById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("该用户不存在"));
    }

    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }
}
