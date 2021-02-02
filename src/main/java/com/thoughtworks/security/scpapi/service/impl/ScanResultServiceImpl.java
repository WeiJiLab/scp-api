package com.thoughtworks.security.scpapi.service.impl;


import com.thoughtworks.security.scpapi.entity.ScanResultEntity;
import com.thoughtworks.security.scpapi.entity.ScanTaskEntity;
import com.thoughtworks.security.scpapi.repository.ScanResultRepository;
import com.thoughtworks.security.scpapi.repository.ScanTaskRepository;
import com.thoughtworks.security.scpapi.service.ScanResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScanResultServiceImpl implements ScanResultService {

    private final ScanResultRepository scanResultRepository;
    private final ScanTaskRepository scanTaskRepository;

    @Override
    public List<ScanResultEntity> findByAppId(Integer appId) {

        List<ScanTaskEntity> allByAppId = scanTaskRepository.findAllByAppId(appId);

        List<Integer> taskIds = allByAppId.stream()
                .map(ScanTaskEntity::getId)
                .collect(Collectors.toList());

        return  scanResultRepository.findAllByScanTaskIdIn(taskIds);
    }
}
