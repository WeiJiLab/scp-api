package com.thoughtworks.security.scpapi.service;


import com.thoughtworks.security.scpapi.domain.ScanResult;

import java.util.List;

public interface ScanResultService {
    List<ScanResult> findByAppId(Long appId);

    ScanResult findByTaskId(Long taskIn);

    ScanResult findById(Long resultId);
}
