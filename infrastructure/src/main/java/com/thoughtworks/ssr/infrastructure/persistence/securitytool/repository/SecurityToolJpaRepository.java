package com.thoughtworks.ssr.infrastructure.persistence.securitytool.repository;

import com.thoughtworks.ssr.infrastructure.persistence.securitytool.entity.SecurityToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityToolJpaRepository extends JpaRepository<SecurityToolEntity, Long> {
    Optional<SecurityToolEntity> findByName(String name);

    boolean existsByName(String name);
}
