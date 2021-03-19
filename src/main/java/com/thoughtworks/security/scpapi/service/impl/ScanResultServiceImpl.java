package com.thoughtworks.security.scpapi.service.impl;

import com.thoughtworks.security.scpapi.domain.ScanResult;
import com.thoughtworks.security.scpapi.entity.ScanResultEntity;
import com.thoughtworks.security.scpapi.entity.ScanTaskEntity;
import com.thoughtworks.security.scpapi.entity.UseCaseEntity;
import com.thoughtworks.security.scpapi.exception.core.NotFoundException;
import com.thoughtworks.security.scpapi.repository.ScanResultRepository;
import com.thoughtworks.security.scpapi.repository.ScanTaskRepository;
import com.thoughtworks.security.scpapi.repository.UseCaseRepository;
import com.thoughtworks.security.scpapi.service.ScanResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.security.scpapi.exception.core.NotFoundError.NOT_FOUND_SCAN_RESULT;
import static com.thoughtworks.security.scpapi.exception.core.NotFoundError.NOT_FOUND_USE_CASE;

@Service
@RequiredArgsConstructor
public class ScanResultServiceImpl implements ScanResultService {

    private final ScanResultRepository scanResultRepository;
    private final ScanTaskRepository scanTaskRepository;
    private final UseCaseRepository useCaseRepository;

    @Override
    public List<ScanResult> findByAppId(Long appId) {

        List<ScanTaskEntity> allByAppId = scanTaskRepository.findAllByAppId(appId);

        List<Long> taskIds = allByAppId.stream()
                .map(ScanTaskEntity::getId)
                .collect(Collectors.toList());

        List<ScanResultEntity> scanResultEntities =
                scanResultRepository.findAllByScanTaskIdIn(taskIds);

        return scanResultEntities.stream()
                .map(this::generateScanResult)
                .collect(Collectors.toList());
    }

    @Override
    public ScanResult findByTaskId(Long taskIn) {
        ScanResultEntity scanResultEntity = scanResultRepository.findByScanTaskId(taskIn)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_SCAN_RESULT));

        return generateScanResult(scanResultEntity);

    }

    @Override
    public ScanResult findById(Long resultId) {
        ScanResultEntity scanResultEntity = scanResultRepository.findById(resultId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_SCAN_RESULT));
        return generateScanResult(scanResultEntity);
    }

    private ScanResult generateScanResult(ScanResultEntity scanResultEntity) {
        UseCaseEntity useCaseEntity = useCaseRepository.findById(scanResultEntity.getUseCaseId())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USE_CASE));

        return ScanResult.builder()
                .id(scanResultEntity.getId())
                .result(scanResultEntity.getResult())
                .resultPath(scanResultEntity.getResultPath())
                .scanTaskId(scanResultEntity.getScanTaskId())
                .useCaseName(useCaseEntity.getName())
                .createdAt(scanResultEntity.getCreatedAt())
                .updatedAt(scanResultEntity.getUpdatedAt())
                .build();
    }
}
