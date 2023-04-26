package com.thoughtworks.ssr.business.project.rest;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.thoughtworks.ssr.business.project.service.K8sClusterBusinessService;
import com.thoughtworks.ssr.business.project.service.ProjectBusinessService;
import com.thoughtworks.ssr.business.project.usecases.*;
import com.thoughtworks.ssr.domain.project.exception.K8sClusterException;
import com.thoughtworks.ssr.domain.project.query.K8sClusterQuery;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@RequestMapping("/api/business/projects/{pid}/k8s-clusters")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class K8sClusterController {

    private final ProjectBusinessService projectBusinessService;
    private final K8sClusterBusinessService k8sClusterBusinessService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CreateK8sClusterCase.Response create(
            @NotNull @PathVariable("pid") Long projectId,
            @Valid @RequestBody CreateK8sClusterCase.Request request
    ) {
        checkProjectExists(projectId);
        var k8sCluster = k8sClusterBusinessService.create(projectId, request);
        return CreateK8sClusterCase.Response.from(k8sCluster);
    }

    @GetMapping
    @ResponseStatus(OK)
    public Page<GetK8sClusterCase.Response> pageK8sClusters(
            @PageableDefault() Pageable pageable,
            @NotNull @PathVariable("pid") Long projectId,
            @RequestParam(value = "name", required = false) String name
    ) {
        checkProjectExists(projectId);
        var k8sClusterQuery = new K8sClusterQuery(projectId, name);
        var kClss = k8sClusterBusinessService.pageK8sClusters(k8sClusterQuery, pageable);
        return kClss.map(GetK8sClusterCase.Response::from);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public GetK8sClusterCase.Response findById(@NotNull @PathVariable("pid") Long projectId, @PathVariable("id") Long id) {
        var kCls = k8sClusterBusinessService.findById(id);
        if (!Objects.equals(kCls.getProjectId(), projectId)) {
            throw K8sClusterException.notFound();
        }
        return GetK8sClusterCase.Response.from(kCls);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteById(@NotNull @PathVariable("pid") Long projectId, @PathVariable("id") Long id) {
        var kCls = k8sClusterBusinessService.findById(id);
        if (Objects.equals(kCls.getProjectId(), projectId)) {
            k8sClusterBusinessService.deleteById(id);
        }
    }

    @PatchMapping("/{id}")
    @ResponseStatus(OK)
    public UpdateK8sClusterCase.Response updateK8sCluster(
            @NotNull @PathVariable("pid") Long projectId,
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateK8sClusterCase.Request request
    ) {
        checkProjectExists(projectId);
        var kCls =  k8sClusterBusinessService.updateK8sCluster(id, request);
        return UpdateK8sClusterCase.Response.from(kCls);
    }

    private void checkProjectExists(Long projectId) {
        projectBusinessService.findById(projectId);
    }

}
