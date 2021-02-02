package com.thoughtworks.security.scpapi.repository;

import com.thoughtworks.security.scpapi.entity.ScanTaskEntity;
import com.thoughtworks.security.scpapi.enums.ScanTaskEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ScanTaskRepository extends JpaRepository<ScanTaskEntity, Integer> {

    List<ScanTaskEntity> findAllByAppId(Integer appId);

    List<ScanTaskEntity> findAllByAppIdIn(Collection<Integer> appIds);

    List<ScanTaskEntity> findAllByToolId(Integer toolId);

    List<ScanTaskEntity> findAllByUseCaseId(Long useCaseId);

    List<ScanTaskEntity> findAllByUseCaseIdIn(Collection<Long> useCaseId);

    List<ScanTaskEntity> findAllByStatus(ScanTaskEnum status);
}

