package com.thoughtworks.ssr.business.scan.service;

import com.thoughtworks.ssr.domain.scan.model.ScanResult;
import com.thoughtworks.ssr.domain.scan.service.ScanResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScanResultBusinessService {
    private final ScanResultService scanResultService;

    public ScanResult findById(Long id) {
        return scanResultService.findById(id);
    }
}
