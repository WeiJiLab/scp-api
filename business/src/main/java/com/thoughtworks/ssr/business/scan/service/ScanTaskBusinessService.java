package com.thoughtworks.ssr.business.scan.service;

import com.thoughtworks.ssr.domain.scan.model.ScanTask;
import com.thoughtworks.ssr.domain.scan.service.ScanTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScanTaskBusinessService {
    private final ScanTaskService scanTaskService;

    public ScanTask findById(Long id) {
        return scanTaskService.findById(id);
    }

    public void deleteById(Long id) {
        scanTaskService.deleteById(id);
    }
}
