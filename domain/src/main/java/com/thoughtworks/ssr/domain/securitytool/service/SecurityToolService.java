package com.thoughtworks.ssr.domain.securitytool.service;

import com.thoughtworks.ssr.domain.securitytool.exception.SecurityToolException;
import com.thoughtworks.ssr.domain.securitytool.model.SecurityTool;
import com.thoughtworks.ssr.domain.securitytool.repository.SecurityToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityToolService {

    private final SecurityToolRepository securityToolRepository;

    public SecurityTool create(SecurityTool securityTool) {
        return securityToolRepository.create(securityTool);
    }

    public SecurityTool findById(Long id) {
        return securityToolRepository.findById(id).orElseThrow(SecurityToolException::notFound);
    }

    public Page<SecurityTool> pageSecurityTool(Pageable pageable) {
        return securityToolRepository.pageSecurityTool(pageable);
    }

    public List<SecurityTool> findAll() {
        return null;
    }
}
