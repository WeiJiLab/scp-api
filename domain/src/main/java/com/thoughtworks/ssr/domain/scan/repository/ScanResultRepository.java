package com.thoughtworks.ssr.domain.scan.repository;

import com.thoughtworks.ssr.domain.scan.model.ScanResult;

import java.util.Optional;

public interface ScanResultRepository {

    ScanResult create(ScanResult scanResult);

    ScanResult update(ScanResult scanResult);

    Optional<ScanResult> findById(Long id);

    Optional<ScanResult> findByTaskId(Long taskId);
}
