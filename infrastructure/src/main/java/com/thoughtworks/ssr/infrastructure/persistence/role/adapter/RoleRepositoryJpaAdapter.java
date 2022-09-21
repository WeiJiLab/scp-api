package com.thoughtworks.ssr.infrastructure.persistence.role.adapter;

import com.thoughtworks.ssr.domain.role.model.ERole;
import com.thoughtworks.ssr.domain.role.model.Role;
import com.thoughtworks.ssr.domain.role.repository.RoleRepository;
import com.thoughtworks.ssr.infrastructure.persistence.role.converter.RoleEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.role.repository.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RoleRepositoryJpaAdapter implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Set<Role> findAll() {
        var roles = roleJpaRepository.findAll();
        return RoleEntityConverter.convertTo(roles);
    }

    @Override
    public Optional<Role> findByRole(ERole role) {
        return roleJpaRepository.findByRole(role)
                .map(RoleEntityConverter::convertTo);
    }
}
