package com.thoughtworks.ssr.infrastructure.persistence.usecases.repository;

import com.thoughtworks.ssr.infrastructure.persistence.usecases.entity.UseCaseGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UseCaseGroupJpaRepository extends JpaRepository<UseCaseGroupEntity, Long> {
}
