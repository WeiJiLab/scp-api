package com.thoughtworks.ssr.infrastructure.persistence.iam.repository;

import com.thoughtworks.ssr.domain.iam.model.ERole;
import com.thoughtworks.ssr.infrastructure.persistence.iam.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRole(ERole role);
}

