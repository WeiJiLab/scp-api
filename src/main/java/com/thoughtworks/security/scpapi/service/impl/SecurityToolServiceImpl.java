package com.thoughtworks.security.scpapi.service.impl;

import com.thoughtworks.security.scpapi.entity.SecurityTool;
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
        securityToolRepository.findByName(securityTool.getName()).ifPresent(existedProject -> {
            throw new DuplicatedSecurityToolException();
        });

        return securityToolRepository.saveAndFlush(securityTool);
    }

    @Override
    public SecurityTool findById(Integer id) {
        return securityToolRepository.findById(id).orElseThrow(SecurityToolNotFoundException::new);
    }

    @Override
    public List<SecurityTool> findAll() {
        return securityToolRepository.findAll();
    }
}
