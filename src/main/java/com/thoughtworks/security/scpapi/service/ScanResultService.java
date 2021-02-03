package com.thoughtworks.security.scpapi.service;


import com.thoughtworks.security.scpapi.domain.ScanResult;

import java.util.List;

public interface ScanResultService {
    List<ScanResult> findByAppId(Integer appId);

    ScanResult findByTaskId(Integer taskIn);

    ScanResult findById(Long resultId);
}
