package com.thoughtworks.ssr.business.project.rest;

import com.thoughtworks.ssr.business.common.constants.QueryConstants;
import com.thoughtworks.ssr.business.project.service.ProjectBusinessService;
import com.thoughtworks.ssr.business.project.usecases.CreateProjectCase;
import com.thoughtworks.ssr.business.project.usecases.GetProjectCase;
import com.thoughtworks.ssr.business.project.usecases.UpdateProjectCase;
import com.thoughtworks.ssr.common.annotation.CurrentUser;
import com.thoughtworks.ssr.domain.user.model.CustomUserDetails;
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

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/business/projects")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class ProjectController {

    private final ProjectBusinessService projectBusinessService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CreateProjectCase.Response create(
            @CurrentUser CustomUserDetails userDetails,
            @Valid @RequestBody CreateProjectCase.Request request
    ) {
        var project = projectBusinessService.create(request, userDetails);
        return CreateProjectCase.Response.from(project);
    }

    @GetMapping
    @ResponseStatus(OK)
    public Page<GetProjectCase.Response> pageProjects(
            @PageableDefault(size = QueryConstants.DEFAULT_PAGE_SIZE) Pageable pageable,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "ownerId", required = false) Long ownerId
    ) {
        var projects = projectBusinessService.pageProjects(pageable, name, ownerId);
        return projects.map(GetProjectCase.Response::from);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public GetProjectCase.Response findById(@PathVariable("id") Long id) {
        var project = projectBusinessService.findById(id);
        return GetProjectCase.Response.from(project);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteById(@PathVariable("id") Long id) {
        projectBusinessService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(OK)
    public UpdateProjectCase.Response updateProject(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateProjectCase.Request request
    ) {
        var project =  projectBusinessService.updateProject(id, request);
        return UpdateProjectCase.Response.from(project);
    }

}
