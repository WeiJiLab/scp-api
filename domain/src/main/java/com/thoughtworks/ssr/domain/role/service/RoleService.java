package com.thoughtworks.ssr.domain.role.service;

import com.thoughtworks.ssr.domain.role.exception.RoleException;
import com.thoughtworks.ssr.domain.role.model.ERole;
import com.thoughtworks.ssr.domain.role.model.Role;
import com.thoughtworks.ssr.domain.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.thoughtworks.ssr.domain.role.model.ERole.ROLE_ADMIN;
import static com.thoughtworks.ssr.domain.role.model.ERole.ROLE_USER;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Set<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findRoleByRole(ERole role) {
        return roleRepository.findByRole(role)
                .orElseThrow(RoleException::notFound);
    }

    public Set<Role> buildRoleWithUser() {
        Set<Role> roles = new HashSet<>();
        roles.add(findRoleByRole(ROLE_USER));
        return roles;
    }

    public Set<Role> buildRoleWithAdmin() {
        Set<Role> roles = new HashSet<>();
        roles.add(findRoleByRole(ROLE_ADMIN));
        return roles;
    }
}
