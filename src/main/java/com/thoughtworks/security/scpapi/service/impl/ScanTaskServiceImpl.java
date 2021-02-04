package com.thoughtworks.security.scpapi.service.impl;

import com.thoughtworks.security.scpapi.controller.request.ScanTaskRequest;
import com.thoughtworks.security.scpapi.controller.request.TaskSearchRequest;
import com.thoughtworks.security.scpapi.entity.Application;
import com.thoughtworks.security.scpapi.entity.ScanTaskEntity;
import com.thoughtworks.security.scpapi.enums.ScanTaskEnum;
import com.thoughtworks.security.scpapi.exception.ScanTaskNotFoundException;
import com.thoughtworks.security.scpapi.repository.ScanResultRepository;
import com.thoughtworks.security.scpapi.repository.ScanTaskRepository;
import com.thoughtworks.security.scpapi.repository.UseCaseRepository;
import com.thoughtworks.security.scpapi.service.ComplianceScanThread;
import com.thoughtworks.security.scpapi.service.ProjectService;
import com.thoughtworks.security.scpapi.service.ScanTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScanTaskServiceImpl implements ScanTaskService {

    private final ScanTaskRepository scanTaskRepository;

    private final UseCaseRepository useCaseRepository;

    private final ScanResultRepository scanResultRepository;

    private final ProjectService projectService;

    @Override
    public List<ScanTaskEntity> create(ScanTaskRequest scanTaskRequest) {

        List<Long> useCaseIds = scanTaskRequest.getUseCaseIds();

        var scanTaskEntities = useCaseRepository
                .findAllByIdIn(useCaseIds).stream()
                .map(it -> ScanTaskEntity.builder()
                        .appId(scanTaskRequest.getAppId())
                        .toolId(it.getSecurityToolId())
                        .useCaseId(it.getId())
                        .status(ScanTaskEnum.READY)
                        .build())
                .collect(Collectors.toList());

        List<ScanTaskEntity> scanTaskEntityList = scanTaskRepository.saveAll(scanTaskEntities);

        startScan(scanTaskEntityList);

        return scanTaskEntityList;

    }

    private void startScan(List<ScanTaskEntity> scanTaskEntityList) {

        // TODO : start scan
        scanTaskEntityList
                .forEach(it ->
                        new ComplianceScanThread(it,
                                scanResultRepository,
                                scanTaskRepository,
                                useCaseRepository
                        ).start()
                );
    }

    @Override
    public List<ScanTaskEntity> search(TaskSearchRequest request) {

        List<ScanTaskEntity> scanTaskEntities;

        if (request.getAppId() != null) {
            scanTaskEntities = getScanTaskByAppId(request.getAppId());
        } else if (request.getProjectId() != null) {
            scanTaskEntities = getScanTaskByProjectId(request.getProjectId());
        } else if (request.getToolId() != null) {
            scanTaskEntities = getScanTaskByToolIs(request.getToolId());
        } else if (request.getUseCaseId() != null) {
            scanTaskEntities = getScanTaskByUseCaseId(request.getUseCaseId());
        } else if (request.getStatus() != null) {
            scanTaskEntities = getScanTaskByStatus(request.getStatus());
        } else {
            scanTaskEntities = scanTaskRepository.findAll();
        }

        return scanTaskEntities.stream()
                .sorted(Comparator.comparing(ScanTaskEntity::getCreatedAt))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScanTaskEntity> getScanTaskByStatus(ScanTaskEnum status) {
        return scanTaskRepository.findAllByStatus(status);
    }

    @Override
    public List<ScanTaskEntity> getScanTaskByAppId(Integer appId) {
        return scanTaskRepository.findAllByAppId(appId);
    }

    @Override
    public List<ScanTaskEntity> getScanTaskByToolIs(Integer toolId) {
        return scanTaskRepository.findAllByToolId(toolId);
    }

    @Override
    public List<ScanTaskEntity> getScanTaskByUseCaseId(Long useCaseId) {
        return scanTaskRepository.findAllByUseCaseId(useCaseId);
    }

    @Override
    public List<ScanTaskEntity> getScanTaskByProjectId(Integer projectId) {

        List<Application> applications = projectService.getApplications(projectId);
        List<Integer> appIds = applications.stream()
                .map(Application::getId)
                .collect(Collectors.toList());
        return scanTaskRepository.findAllByAppIdIn(appIds);
    }

    @Override
    public ScanTaskEntity findById(Integer id) {
        return scanTaskRepository.findById(id)
                .orElseThrow(ScanTaskNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        try {
            useCaseRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new ScanTaskNotFoundException();
        }
    }


}
