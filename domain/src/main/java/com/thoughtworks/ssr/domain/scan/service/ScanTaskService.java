package com.thoughtworks.ssr.domain.scan.service;

import com.thoughtworks.ssr.domain.scan.exception.ScanTaskException;
import com.thoughtworks.ssr.domain.scan.model.ScanTask;
import com.thoughtworks.ssr.domain.scan.repository.ScanTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScanTaskService {
    private final ScanTaskRepository scanTaskRepository;

    public ScanTask create(ScanTask scanTask) {
        return scanTaskRepository.create(scanTask);
    }

    public ScanTask update(ScanTask scanTask) {
        return scanTaskRepository.update(scanTask);
    }

    public ScanTask findById(Long id) {
        return scanTaskRepository.findById(id).orElseThrow(ScanTaskException::notFound);
    }

    public void deleteById(Long id) {
        scanTaskRepository.deleteById(id);
    }

    public Page<ScanTask> pageScanTask(Pageable pageable) {
       return scanTaskRepository.pageScanTask(pageable);
    }

}
