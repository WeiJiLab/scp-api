package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.entity.ComplianceResultEntity;
import org.springframework.data.domain.Page;

public interface SecurityResultService {
    Page<ComplianceResultEntity> getAllSecurityResult(Integer page, Integer size);

}
