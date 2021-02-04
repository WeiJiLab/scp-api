package com.thoughtworks.security.scpapi.service.impl;

import com.thoughtworks.security.scpapi.entity.ScanResultEntity;
import com.thoughtworks.security.scpapi.repository.ScanResultRepository;
import com.thoughtworks.security.scpapi.repository.UseCaseRepository;
import com.thoughtworks.security.scpapi.service.ComplianceScanThread;
import com.thoughtworks.security.scpapi.service.StartComplianceService;
import com.thoughtworks.security.scpapi.util.ScanResultEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class StartComplianceServiceImpl implements StartComplianceService {
    private final ScanResultRepository complianceResultRepo;
    private final UseCaseRepository complianceUseCaseRepo;
    private ArrayList<ScanResultEntity> insertScanResult(Integer taskId, Long[] useCaseIds) {
        ArrayList<ScanResultEntity> resultList = new ArrayList<ScanResultEntity>();
        for (Long useCaseId : useCaseIds) {
            ScanResultEntity result = ScanResultEntity.builder()
                    .useCaseId(useCaseId)
                    .result(ScanResultEnum.SCANNING.getValue())
                    .scanTaskId(taskId)
                    .build();
            ScanResultEntity saveRes = complianceResultRepo.save(result);
            resultList.add(saveRes);
        }
        return resultList;
    }
    @Override
    public void start(Integer taskId, Long[] useCaseIds) {
        ArrayList<ScanResultEntity> securityResults = insertScanResult(taskId, useCaseIds);
        for (ScanResultEntity tmp : securityResults) {
//            ComplianceScanThread securityScanThread = new ComplianceScanThread(tmp.getId(), tmp.getUseCaseId(), complianceResultRepo, complianceUseCaseRepo);
//            securityScanThread.start();
        }
    }
}
