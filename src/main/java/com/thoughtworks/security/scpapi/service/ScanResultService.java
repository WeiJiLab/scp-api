package com.thoughtworks.security.scpapi.service;


import com.thoughtworks.security.scpapi.entity.ScanResultEntity;

import java.util.List;

public interface ScanResultService {
    List<ScanResultEntity> findByAppId(Integer appId);
}
