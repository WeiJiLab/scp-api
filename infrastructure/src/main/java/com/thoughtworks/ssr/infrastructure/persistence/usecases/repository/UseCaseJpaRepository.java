package com.thoughtworks.ssr.infrastructure.persistence.usecases.repository;

import com.thoughtworks.ssr.infrastructure.persistence.usecases.entity.UseCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UseCaseJpaRepository extends JpaRepository<UseCaseEntity, Long> {
}
