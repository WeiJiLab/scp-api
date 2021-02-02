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

    List<ScanTaskEntity> getScanTaskByAppId(Integer appId);

    List<ScanTaskEntity> getScanTaskByToolIs(Integer toolId);

    List<ScanTaskEntity> getScanTaskByUseCaseId(Integer sseCaseId);

    List<ScanTaskEntity> getScanTaskByProjectId(Integer projectId);

    ScanTaskEntity findById(Integer id);

    void delete(Long id);
}
