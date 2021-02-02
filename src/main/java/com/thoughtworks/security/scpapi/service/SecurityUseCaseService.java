package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.dto.ComplianceUseCaseDto;
import org.springframework.data.domain.Page;

public interface SecurityUseCaseService {
    Page<ComplianceUseCaseDto> getUseCaseList(Integer page, Integer size);
}
