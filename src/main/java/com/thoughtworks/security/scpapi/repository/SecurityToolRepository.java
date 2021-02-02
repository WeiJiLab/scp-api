package com.thoughtworks.security.scpapi.repository;

import com.thoughtworks.security.scpapi.entity.SecurityTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityToolRepository extends JpaRepository<SecurityTool, Integer> {
    Optional<SecurityTool> findByName(String name);
}
