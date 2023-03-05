package com.thoughtworks.ssr.infrastructure.persistence.iam.adapter;

import com.thoughtworks.ssr.domain.iam.model.ERole;
import com.thoughtworks.ssr.domain.iam.model.Role;
import com.thoughtworks.ssr.domain.iam.repository.RoleRepository;
import com.thoughtworks.ssr.infrastructure.persistence.iam.converter.RoleEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.iam.repository.RoleJpaRepository;
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
