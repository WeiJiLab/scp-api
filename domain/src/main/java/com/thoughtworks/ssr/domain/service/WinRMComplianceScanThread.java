package com.thoughtworks.ssr.domain.service;

import com.thoughtworks.ssr.domain.scan.model.ScanTask;
import com.thoughtworks.ssr.domain.scan.service.ScanResultService;
import com.thoughtworks.ssr.domain.scan.service.ScanTaskService;
import com.thoughtworks.ssr.domain.usecase.service.UseCaseService;

public class WinRMComplianceScanThread extends ComplianceScanThread {

    public WinRMComplianceScanThread(ScanTask scanTask, ScanResultService scanResultService, ScanTaskService scanTaskService, UseCaseService useCaseService, EnvironmentTypePara environmentTypePara) {
        super(scanTask, scanResultService, scanTaskService, useCaseService, environmentTypePara);
    }

    @Override
    public String getScanEnvironmentCmd() {
        EnvironmentTypePara environmentTypePara = getEnvironmentPara();
        return " -t winrm://" + environmentTypePara.getUserName() + ":" + environmentTypePara.getPassword()
                + "@" + environmentTypePara.getAddr();
    }
}
