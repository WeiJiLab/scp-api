package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.controller.request.ScanTaskRequest;
import com.thoughtworks.security.scpapi.controller.request.TaskSearchRequest;
import com.thoughtworks.security.scpapi.entity.ScanTaskEntity;
import com.thoughtworks.security.scpapi.enums.ScanTaskEnum;

import java.util.List;

public interface ScanTaskService {
    List<ScanTaskEntity> create(ScanTaskRequest scanTaskRequest);

    List<ScanTaskEntity> search(TaskSearchRequest request);

    List<ScanTaskEntity> getScanTaskByStatus(ScanTaskEnum status);

    List<ScanTaskEntity> getScanTaskByAppId(Long appId);

    List<ScanTaskEntity> getScanTaskByToolIs(Long toolId);

    List<ScanTaskEntity> getScanTaskByUseCaseId(Long sseCaseId);

    List<ScanTaskEntity> getScanTaskByProjectId(Long projectId);

    ScanTaskEntity findById(Long id);

    void delete(Long id);
}
