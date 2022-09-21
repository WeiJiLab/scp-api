package com.thoughtworks.ssr.infrastructure.persistence.admin.adapter;

import com.thoughtworks.ssr.domain.admin.model.Admin;
import com.thoughtworks.ssr.domain.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminRepositoryAdapter implements AdminRepository {
    @Override
    public Optional<Admin> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<Admin> findByIdAndActiveIsTrue(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Admin> findByPhoneNumber(String phoneNumber) {
        return Optional.empty();
    }

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return null;
    }

    @Override
    public Admin save(Admin admin) {
        return null;
    }

    @Override
    public Optional<Admin> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
