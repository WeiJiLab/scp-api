package com.thoughtworks.ssr.infrastructure.persistence.scan.adapter;

import com.thoughtworks.ssr.domain.scan.model.ScanResult;
import com.thoughtworks.ssr.domain.scan.repository.ScanResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ScanResultRepositoryAdapter implements ScanResultRepository {
    @Override
    public ScanResult create(ScanResult scanResult) {
        return null;
    }

    @Override
    public ScanResult update(ScanResult scanResult) {
        return null;
    }

    @Override
    public Optional<ScanResult> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ScanResult> findByTaskId(Long taskId) {
        return Optional.empty();
    }
}
