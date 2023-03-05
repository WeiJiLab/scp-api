package com.thoughtworks.ssr.business.project.rest;

import com.thoughtworks.ssr.business.common.constants.QueryConstants;
import com.thoughtworks.ssr.business.project.service.AppInfoBusinessService;
import com.thoughtworks.ssr.business.project.usecases.CreateAppInfoCase;
import com.thoughtworks.ssr.business.project.usecases.GetAppInfoCase;
import com.thoughtworks.ssr.business.project.usecases.UpdateAppInfoCase;
import com.thoughtworks.ssr.common.annotation.CurrentUser;
import com.thoughtworks.ssr.domain.core.enums.RepoType;
import com.thoughtworks.ssr.domain.project.query.AppInfoQuery;
import com.thoughtworks.ssr.domain.iam.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/business/app-infos")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class AppInfoController {

    private final AppInfoBusinessService appInfoBusinessService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Long create(
            @CurrentUser CustomUserDetails userDetails,
            @Valid @RequestBody CreateAppInfoCase.Request request
    ) {
        return appInfoBusinessService.create(request, userDetails);
    }

    @GetMapping
    @ResponseStatus(OK)
    public Page<GetAppInfoCase.Response> pageProjects(
            @PageableDefault(size = QueryConstants.DEFAULT_PAGE_SIZE) Pageable pageable,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "repoType", required = false) RepoType repoType
    ) {
        var appInfoQuery = new AppInfoQuery(name, username, repoType);
        var appInfos = appInfoBusinessService.pageAppInfo(appInfoQuery, pageable);
        return appInfos.map(GetAppInfoCase.Response::from);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public GetAppInfoCase.Response findById(@PathVariable("id") Long id) {
        var appInfo = appInfoBusinessService.findById(id);
        return GetAppInfoCase.Response.from(appInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteById(@PathVariable("id") Long id) {
        appInfoBusinessService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(OK)
    public void updateProject(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateAppInfoCase.Request request
    ) {
        appInfoBusinessService.updateAppInfo(id, request);
    }

}
