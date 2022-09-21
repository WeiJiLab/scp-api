package com.thoughtworks.ssr.infrastructure.persistence.scan.adapter;

import com.thoughtworks.ssr.domain.scan.model.ScanTask;
import com.thoughtworks.ssr.domain.scan.repository.ScanTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ScanTaskRepositoryAdapter implements ScanTaskRepository {
    @Override
    public ScanTask create(ScanTask scanTask) {
        return null;
    }

    @Override
    public ScanTask update(ScanTask scanTask) {
        return null;
    }

    @Override
    public Optional<ScanTask> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Page<ScanTask> pageScanTask(Pageable pageable) {
        return null;
    }
}
