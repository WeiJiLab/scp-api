package com.thoughtworks.security.scpapi.repository;

import com.thoughtworks.security.scpapi.entity.SecurityToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityToolRepository extends JpaRepository<SecurityToolEntity, Long> {
    Optional<SecurityToolEntity> findByName(String name);

    boolean existsByName(String name);
}
