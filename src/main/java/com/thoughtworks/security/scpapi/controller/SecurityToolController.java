package com.thoughtworks.security.scpapi.controller;

import com.thoughtworks.security.scpapi.controller.request.CreateSecurityToolRequest;
import com.thoughtworks.security.scpapi.controller.response.SecurityToolResponse;
import com.thoughtworks.security.scpapi.domain.SecurityTool;
import com.thoughtworks.security.scpapi.entity.UseCaseEntity;
import com.thoughtworks.security.scpapi.service.SecurityToolService;
import com.thoughtworks.security.scpapi.service.UseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/tools")
@RequiredArgsConstructor
public class SecurityToolController {

    private final SecurityToolService service;

    private final UseCaseService useCaseService;

    @PostMapping
    @ResponseStatus(CREATED)
    public SecurityToolResponse create(@Validated @RequestBody CreateSecurityToolRequest request) {
        SecurityTool securityTool = service.create(SecurityTool.from(request));
        return SecurityToolResponse.fromSecurityTool(securityTool);
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public SecurityToolResponse findById(@PathVariable Long id) {
        SecurityTool securityTool = service.findById(id);
        return SecurityToolResponse.fromSecurityTool(securityTool);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<SecurityToolResponse> findAll() {
        List<SecurityTool> securityTools = service.findAll();
        return SecurityToolResponse.fromSecurityTools(securityTools);
    }

    @GetMapping("{id}/useCases")
    @ResponseStatus(OK)
    public List<UseCaseEntity> findUseCaseByToolId(@PathVariable Long id) {
        return useCaseService.findUseCaseByToolId(id);
    }
}
