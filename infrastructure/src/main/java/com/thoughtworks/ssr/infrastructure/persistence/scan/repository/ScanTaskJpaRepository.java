package com.thoughtworks.ssr.infrastructure.persistence.scan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.scan.entity.ScanTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScanTaskJpaRepository extends JpaRepository<ScanTaskEntity, Long> {
}

