package com.thoughtworks.ssr.domain.scan.service;

import com.thoughtworks.ssr.domain.scan.exception.ScanResultException;
import com.thoughtworks.ssr.domain.scan.model.ScanResult;
import com.thoughtworks.ssr.domain.scan.repository.ScanResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScanResultService {
    private final ScanResultRepository scanResultRepository;

    public ScanResult create(ScanResult scanResult) {
        return scanResultRepository.create(scanResult);
    }

    public ScanResult update(ScanResult scanResult) {
        return scanResultRepository.update(scanResult);
    }

    public ScanResult findById(Long id) {
        return scanResultRepository.findById(id).orElseThrow(ScanResultException::notFound);
    }

    public ScanResult findByTaskId(Long taskId) {
        return scanResultRepository.findByTaskId(taskId).orElseThrow(ScanResultException::notFound);
    }
}
