package com.thoughtworks.ssr.domain.admin.repository;

import com.thoughtworks.ssr.domain.admin.model.Admin;

import java.util.Optional;

public interface AdminRepository {
    Optional<Admin> findByUsername(String username);

    Optional<Admin> findByIdAndActiveIsTrue(Long id);

    Boolean existsByUsername(String username);

    Admin save(Admin admin);

    Optional<Admin> findById(Long id);

    void deleteById(Long id);
}
