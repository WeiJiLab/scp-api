package com.thoughtworks.security.scpapi.service.impl;

import com.thoughtworks.security.scpapi.controller.request.ScanTaskRequest;
import com.thoughtworks.security.scpapi.controller.request.TaskSearchRequest;
import com.thoughtworks.security.scpapi.domain.EnvironmentTypePara;
import com.thoughtworks.security.scpapi.entity.Application;
import com.thoughtworks.security.scpapi.entity.ScanTaskEntity;
import com.thoughtworks.security.scpapi.enums.EnvironmentType;
import com.thoughtworks.security.scpapi.enums.ScanTaskEnum;
import com.thoughtworks.security.scpapi.exception.core.NotFoundException;
import com.thoughtworks.security.scpapi.repository.ScanResultRepository;
import com.thoughtworks.security.scpapi.repository.ScanTaskRepository;
import com.thoughtworks.security.scpapi.repository.UseCaseRepository;
import com.thoughtworks.security.scpapi.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.security.scpapi.exception.core.NotFoundError.NOT_FOUND_SCAN_TASK;

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
        EnvironmentType environmentType = scanTaskRequest.getEnvironmentType();
        EnvironmentTypePara environmentTypePara = EnvironmentTypePara.builder()
                .dockerContainerId(scanTaskRequest.getDockerContainerId())
                .userName(scanTaskRequest.getUserName())
                .password(scanTaskRequest.getPassword())
                .addr(scanTaskRequest.getAddr())
                .build();

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

        startScan(scanTaskEntityList, environmentTypePara, environmentType);

        return scanTaskEntityList;

    }

    private ComplianceScanThread createScanThread(ScanTaskEntity scanTaskEntity, EnvironmentTypePara environmentPara, EnvironmentType environmentType) {
        if (environmentType == EnvironmentType.DOCKER) {
            return new DockerComplianceScanThread(scanTaskEntity, scanResultRepository, scanTaskRepository,
                    useCaseRepository, environmentPara);
        } else if (environmentType == EnvironmentType.LINUX) {
            return new SshComplianceScanThread(scanTaskEntity, scanResultRepository, scanTaskRepository,
                    useCaseRepository, environmentPara);
        } else if (environmentType == EnvironmentType.WINDOWS) {
            return new WinRMComplianceScanThread(scanTaskEntity, scanResultRepository, scanTaskRepository,
                    useCaseRepository, environmentPara);
        }
        return new ComplianceScanThread(scanTaskEntity, scanResultRepository, scanTaskRepository,
                useCaseRepository, environmentPara);

    }

    private void startScan(List<ScanTaskEntity> scanTaskEntityList, EnvironmentTypePara environmentPara, EnvironmentType environmentType) {
        scanTaskEntityList
                .forEach(it -> createScanThread(it, environmentPara, environmentType).start());
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
    public List<ScanTaskEntity> getScanTaskByAppId(Long appId) {
        return scanTaskRepository.findAllByAppId(appId);
    }

    @Override
    public List<ScanTaskEntity> getScanTaskByToolIs(Long toolId) {
        return scanTaskRepository.findAllByToolId(toolId);
    }

    @Override
    public List<ScanTaskEntity> getScanTaskByUseCaseId(Long useCaseId) {
        return scanTaskRepository.findAllByUseCaseId(useCaseId);
    }

    @Override
    public List<ScanTaskEntity> getScanTaskByProjectId(Long projectId) {

        List<Application> applications = projectService.getApplications(projectId);
        List<Long> appIds = applications.stream()
                .map(Application::getId)
                .collect(Collectors.toList());
        return scanTaskRepository.findAllByAppIdIn(appIds);
    }

    @Override
    public ScanTaskEntity findById(Long id) {
        return scanTaskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_SCAN_TASK));
    }

    @Override
    public void delete(Long id) {
        try {
            useCaseRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new NotFoundException(NOT_FOUND_SCAN_TASK);
        }
    }
}
