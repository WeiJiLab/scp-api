package com.thoughtworks.ssr.business.project.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
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

import static com.thoughtworks.ssr.infrastructure.persistence.project.entity.QProjectEntity.projectEntity;

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

    public Page<Project> pageProjects(Pageable pageable, String name, Long ownerId) {

        var predicate = buildPredicate(name, ownerId);

        return projectService.pageProjects(pageable, predicate);
    }

    public Predicate buildPredicate(String name, Long ownerId) {

        var predicate = new BooleanBuilder(null);

        if (!ObjectUtils.isEmpty(name)) {
            predicate = new BooleanBuilder(predicate).and(projectEntity.name.containsIgnoreCase(name));
        }

        if (!ObjectUtils.isEmpty(ownerId)) {
            predicate = new BooleanBuilder(predicate).and(projectEntity.ownerId.eq(ownerId));
        }

        return predicate;
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
