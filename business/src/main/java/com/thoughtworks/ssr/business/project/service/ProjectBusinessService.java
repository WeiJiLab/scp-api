package com.thoughtworks.ssr.business.project.service;

import com.thoughtworks.ssr.business.project.usecases.CreateProjectCase;
import com.thoughtworks.ssr.business.project.usecases.UpdateProjectCase;
import com.thoughtworks.ssr.domain.project.model.Project;
import com.thoughtworks.ssr.domain.project.service.ProjectService;
import com.thoughtworks.ssr.domain.user.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class ProjectBusinessService {
    private final ProjectService projectService;

    public Project create(CreateProjectCase.Request request, CustomUserDetails userDetails) {
        var project = Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .ownerId(userDetails.getId())
                .build();
        return projectService.create(project);
    }

    public Project findById(Long id) {
        return projectService.findById(id);
    }

    public Page<Project> pageProjects(Pageable pageable) {
        return projectService.pageProjects(pageable);
    }

    public void deleteById(Long id) {
        projectService.deleteById(id);
    }

    public Project updateProject(Long id, UpdateProjectCase.Request request) {
        var project = projectService.findById(id);

        if (!ObjectUtils.isEmpty(request.getName())) {
            project.setName(request.getName());
        }

        if (!ObjectUtils.isEmpty(request.getDescription())) {
            project.setDescription(request.getDescription());
        }

        return projectService.update(project);
    }
}
