package com.thoughtworks.ssr.domain.service;

import com.thoughtworks.ssr.domain.scan.model.ScanTask;
import com.thoughtworks.ssr.domain.scan.service.ScanResultService;
import com.thoughtworks.ssr.domain.scan.service.ScanTaskService;
import com.thoughtworks.ssr.domain.usecase.service.UseCaseService;

public class DockerComplianceScanThread extends ComplianceScanThread {

    public DockerComplianceScanThread(ScanTask scanTask, ScanResultService scanResultService, ScanTaskService scanTaskService, UseCaseService useCaseService, EnvironmentTypePara environmentTypePara) {
        super(scanTask, scanResultService, scanTaskService, useCaseService, environmentTypePara);
    }

    @Override
    public String getScanEnvironmentCmd() {
        EnvironmentTypePara environmentTypePara = getEnvironmentPara();
        return " -t docker://" + environmentTypePara.getDockerContainerId();
    }
}
