package com.thoughtworks.ssr.domain.iam.repository;

import com.thoughtworks.ssr.domain.iam.model.ERole;
import com.thoughtworks.ssr.domain.iam.model.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository {
    Set<Role> findAll();

    Optional<Role> findByRole(ERole role);
}
