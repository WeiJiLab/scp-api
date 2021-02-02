package com.thoughtworks.security.scpapi.controller;

import com.thoughtworks.security.scpapi.controller.request.CreateSecurityToolRequest;
import com.thoughtworks.security.scpapi.entity.SecurityTool;
import com.thoughtworks.security.scpapi.entity.UseCaseEntity;
import com.thoughtworks.security.scpapi.service.SecurityToolService;
import com.thoughtworks.security.scpapi.service.UseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("tools")
@RequiredArgsConstructor
public class SecurityToolController {

    private final SecurityToolService service;

    private final UseCaseService useCaseService;

    @PostMapping
    public ResponseEntity<SecurityTool> create(@Validated @RequestBody CreateSecurityToolRequest createSecurityToolRequest) {
        var project = service.create(SecurityTool.from(createSecurityToolRequest));
        return status(CREATED).body(project);
    }

    @GetMapping("{id}")
    public ResponseEntity<SecurityTool> findById(@PathVariable Integer id) {
        var project = service.findById(id);
        return status(OK).body(project);
    }

    @GetMapping
    public ResponseEntity<List<SecurityTool>> findAll() {
        var projects = service.findAll();
        return status(OK).body(projects);
    }

    @GetMapping("{id}/useCases")
    public List<UseCaseEntity> findUseCaseByToolId(@PathVariable Integer id) {
        return useCaseService.findUseCaseByToolId(id);
    }
}
