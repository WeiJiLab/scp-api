package com.thoughtworks.security.scpapi.repository;

import com.thoughtworks.security.scpapi.entity.UseCaseGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UseCaseGroupRepository extends JpaRepository<UseCaseGroupEntity, Integer> {

    boolean existsByName(String name);
}
