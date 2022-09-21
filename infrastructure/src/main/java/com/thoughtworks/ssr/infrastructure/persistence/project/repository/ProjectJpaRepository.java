package com.thoughtworks.ssr.infrastructure.persistence.project.repository;

import com.thoughtworks.ssr.infrastructure.persistence.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Long> {
}
