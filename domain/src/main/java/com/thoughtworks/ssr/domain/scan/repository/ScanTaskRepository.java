package com.thoughtworks.ssr.domain.scan.repository;

import com.thoughtworks.ssr.domain.scan.model.ScanTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ScanTaskRepository {
    ScanTask create(ScanTask scanTask);

    ScanTask update(ScanTask scanTask);

    Optional<ScanTask> findById(Long id);

    void deleteById(Long id);

    Page<ScanTask> pageScanTask(Pageable pageable);

}
