package com.thoughtworks.ssr.infrastructure.persistence.project.repository;

import com.thoughtworks.ssr.infrastructure.persistence.project.entity.K8sClusterEntity;
import com.thoughtworks.ssr.infrastructure.persistence.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface K8sClusterJpaRepository extends
        JpaRepository<K8sClusterEntity, Long>,
        JpaSpecificationExecutor<K8sClusterEntity> {
}
