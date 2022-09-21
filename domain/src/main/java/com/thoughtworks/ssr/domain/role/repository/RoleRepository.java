package com.thoughtworks.ssr.domain.role.repository;

import com.thoughtworks.ssr.domain.role.model.ERole;
import com.thoughtworks.ssr.domain.role.model.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository {
    Set<Role> findAll();

    Optional<Role> findByRole(ERole role);
}
