package com.thoughtworks.security.scpapi.service.impl;


import com.thoughtworks.security.scpapi.domain.ScanResult;
import com.thoughtworks.security.scpapi.entity.ScanResultEntity;
import com.thoughtworks.security.scpapi.entity.ScanTaskEntity;
import com.thoughtworks.security.scpapi.entity.UseCaseEntity;
import com.thoughtworks.security.scpapi.exception.ScanResultNotFoundException;
import com.thoughtworks.security.scpapi.exception.UseCaseNotFoundException;
import com.thoughtworks.security.scpapi.repository.ScanResultRepository;
import com.thoughtworks.security.scpapi.repository.ScanTaskRepository;
import com.thoughtworks.security.scpapi.repository.UseCaseRepository;
import com.thoughtworks.security.scpapi.service.ScanResultService;
import com.thoughtworks.security.scpapi.util.ScanResultEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScanResultServiceImpl implements ScanResultService {

    private final ScanResultRepository scanResultRepository;
    private final ScanTaskRepository scanTaskRepository;
    private final UseCaseRepository useCaseRepository;

    @Override
    public List<ScanResult> findByAppId(Integer appId) {

        List<ScanTaskEntity> allByAppId = scanTaskRepository.findAllByAppId(appId);

        List<Integer> taskIds = allByAppId.stream()
                .map(ScanTaskEntity::getId)
                .collect(Collectors.toList());

        List<ScanResultEntity> scanResultEntities =
                scanResultRepository.findAllByScanTaskIdIn(taskIds);

        return scanResultEntities.stream()
                .map(this::generateScanResult)
                .collect(Collectors.toList());
    }

    @Override
    public ScanResult findByTaskId(Integer taskIn) {
        ScanResultEntity scanResultEntity = scanResultRepository.findByScanTaskId(taskIn)
                .orElseThrow(ScanResultNotFoundException::new);

        return generateScanResult(scanResultEntity);

    }

    @Override
    public ScanResult findById(Integer resultId) {
        ScanResultEntity scanResultEntity = scanResultRepository.findById(resultId)
                .orElseThrow(ScanResultNotFoundException::new);
        return generateScanResult(scanResultEntity);
    }

    private ScanResult generateScanResult(ScanResultEntity scanResultEntity) {
        UseCaseEntity useCaseEntity = useCaseRepository.findById(scanResultEntity.getUseCaseId())
                .orElseThrow(UseCaseNotFoundException::new);

        return ScanResult.builder()
                .id(scanResultEntity.getId())
                .result(ScanResultEnum.parse(scanResultEntity.getResult()))
                .resultPath(scanResultEntity.getResultPath())
                .scanTaskId(scanResultEntity.getScanTaskId())
                .useCaseName(useCaseEntity.getName())
                .createdAt(scanResultEntity.getCreatedAt())
                .updatedAt(scanResultEntity.getUpdatedAt())
                .build();
    }
}
