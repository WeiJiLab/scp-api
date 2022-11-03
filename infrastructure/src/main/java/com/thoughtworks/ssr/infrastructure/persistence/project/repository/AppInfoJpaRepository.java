package com.thoughtworks.ssr.infrastructure.persistence.project.repository;

import com.thoughtworks.ssr.infrastructure.persistence.project.entity.AppInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AppInfoJpaRepository extends
        JpaRepository<AppInfoEntity, Long>,
        QuerydslPredicateExecutor<AppInfoEntity> {
}
