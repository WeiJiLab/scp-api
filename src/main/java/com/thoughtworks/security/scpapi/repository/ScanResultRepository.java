package com.thoughtworks.security.scpapi.repository;

import com.thoughtworks.security.scpapi.entity.ScanResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ScanResultRepository extends JpaRepository<ScanResultEntity, Integer> {


    List<ScanResultEntity> findAllByScanTaskIdIn(Collection<Integer> scanTaskId);

}

