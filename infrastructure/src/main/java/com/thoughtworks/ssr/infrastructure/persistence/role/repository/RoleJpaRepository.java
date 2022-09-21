package com.thoughtworks.ssr.infrastructure.persistence.role.repository;

import com.thoughtworks.ssr.domain.role.model.ERole;
import com.thoughtworks.ssr.infrastructure.persistence.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRole(ERole role);
}

