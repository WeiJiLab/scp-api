package com.thoughtworks.ssr.business.securitytool.rest;

import com.thoughtworks.ssr.business.common.constants.QueryConstants;
import com.thoughtworks.ssr.business.securitytool.service.SecurityToolBusinessService;
import com.thoughtworks.ssr.business.securitytool.usecases.CreateSecurityToolCase;
import com.thoughtworks.ssr.business.securitytool.usecases.GetSecurityToolCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/security-tools")
@RequiredArgsConstructor
public class SecurityToolBusinessController {

    private final SecurityToolBusinessService securityToolBusinessService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CreateSecurityToolCase.Response create(
            @Valid @RequestBody CreateSecurityToolCase.Request request
    ) {
        var securityTool = securityToolBusinessService.create(request);
        return CreateSecurityToolCase.Response.from(securityTool);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public GetSecurityToolCase.Response findById(@PathVariable("id") Long id) {
        var securityTool = securityToolBusinessService.findById(id);
        return GetSecurityToolCase.Response.from(securityTool);
    }

    @GetMapping
    @ResponseStatus(OK)
    public Page<GetSecurityToolCase.Response> findAll(
            @PageableDefault(size = QueryConstants.DEFAULT_PAGE_SIZE) Pageable pageable
    ) {
        var securityTools = securityToolBusinessService.pageSecurityTool(pageable);
        return securityTools.map(GetSecurityToolCase.Response::from);
    }
}
