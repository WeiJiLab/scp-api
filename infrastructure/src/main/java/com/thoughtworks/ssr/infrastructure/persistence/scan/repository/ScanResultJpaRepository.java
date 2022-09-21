package com.thoughtworks.ssr.infrastructure.persistence.scan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.scan.entity.ScanResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ScanResultJpaRepository extends JpaRepository<ScanResultEntity, Long> {

    List<ScanResultEntity> findAllByScanTaskIdIn(Collection<Long> scanTaskId);

    Optional<ScanResultEntity> findByScanTaskId(Long scanTaskId);

}

