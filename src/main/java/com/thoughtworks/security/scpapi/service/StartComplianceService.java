package com.thoughtworks.security.scpapi.service;

public interface StartComplianceService {
//    public void create(Long taskId, Long[] useCaseIds);
//
//    List<ScanTaskEntity> create(ScanTaskRequest startComplianceDto);
//
//    List<ScanTaskEntity> getScanTasksByAppId(Integer id);
    //public void startSecurityCheck(Long id, Long useCaseNo, String reportPath);
    public void start(Integer taskId, Long[] useCaseIds);
}
