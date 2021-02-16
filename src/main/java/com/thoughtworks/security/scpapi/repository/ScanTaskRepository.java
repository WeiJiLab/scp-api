package com.thoughtworks.security.scpapi.repository;

import com.thoughtworks.security.scpapi.entity.ScanTaskEntity;
import com.thoughtworks.security.scpapi.enums.ScanTaskEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ScanTaskRepository extends JpaRepository<ScanTaskEntity, Long> {

    List<ScanTaskEntity> findAllByAppId(Long appId);

    List<ScanTaskEntity> findAllByAppIdIn(Collection<Long> appId);

    List<ScanTaskEntity> findAllByToolId(Long toolId);

    List<ScanTaskEntity> findAllByUseCaseId(Long useCaseId);

    List<ScanTaskEntity> findAllByUseCaseIdIn(Collection<Long> useCaseId);

    List<ScanTaskEntity> findAllByStatus(ScanTaskEnum status);
}

