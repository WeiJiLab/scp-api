package com.thoughtworks.ssr.business.securitytool.service;

import com.thoughtworks.ssr.business.securitytool.usecases.CreateSecurityToolCase;
import com.thoughtworks.ssr.domain.securitytool.model.SecurityTool;
import com.thoughtworks.ssr.domain.securitytool.service.SecurityToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityToolBusinessService {
    private final SecurityToolService securityToolService;

    public SecurityTool create(CreateSecurityToolCase.Request request) {

        var securityTool = SecurityTool.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .metadata(request.getMetadata())
                .build();

        return securityToolService.create(securityTool);
    }

    public SecurityTool findById(Long id) {
       return securityToolService.findById(id);
    }

    public Page<SecurityTool> pageSecurityTool(Pageable pageable) {
       return securityToolService.pageSecurityTool(pageable);
    }
}
