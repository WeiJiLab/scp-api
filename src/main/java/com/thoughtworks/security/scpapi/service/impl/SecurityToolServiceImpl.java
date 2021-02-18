package com.thoughtworks.security.scpapi.service.impl;

import com.thoughtworks.security.scpapi.domain.SecurityTool;
import com.thoughtworks.security.scpapi.entity.SecurityToolEntity;
import com.thoughtworks.security.scpapi.exception.DuplicatedSecurityToolException;
import com.thoughtworks.security.scpapi.exception.SecurityToolNotFoundException;
import com.thoughtworks.security.scpapi.repository.SecurityToolRepository;
import com.thoughtworks.security.scpapi.service.SecurityToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityToolServiceImpl implements SecurityToolService {

    private final SecurityToolRepository securityToolRepository;

    @Override
    public SecurityTool create(SecurityTool securityTool) {

        if (securityToolRepository.existsByName(securityTool.getName())) {
            throw new DuplicatedSecurityToolException();
        }

        SecurityToolEntity securityToolEntity = SecurityToolEntity.builder()
                .name(securityTool.getName())
                .category(securityTool.getCategory())
                .description(securityTool.getDescription())
                .metadata(securityTool.getMetadata())
                .build();

        var saveBySecurityToolEntity = securityToolRepository.saveAndFlush(securityToolEntity);

        return SecurityToolEntity.toSecurityTool(saveBySecurityToolEntity);
    }

    @Override
    public SecurityTool findById(Long id) {
        SecurityToolEntity securityToolEntity = securityToolRepository.findById(id)
                .orElseThrow(SecurityToolNotFoundException::new);

        return SecurityToolEntity.toSecurityTool(securityToolEntity);
    }

    @Override
    public List<SecurityTool> findAll() {
        List<SecurityToolEntity> securityToolEntities = securityToolRepository.findAll();

        return SecurityToolEntity.toSecurityTools(securityToolEntities);
    }
}
