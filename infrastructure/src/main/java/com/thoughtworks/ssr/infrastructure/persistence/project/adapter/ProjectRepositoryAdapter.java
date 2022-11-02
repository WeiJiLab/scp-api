package com.thoughtworks.ssr.infrastructure.persistence.project.adapter;

import com.querydsl.core.types.Predicate;
import com.thoughtworks.ssr.domain.project.model.Project;
import com.thoughtworks.ssr.domain.project.repository.ProjectRepository;
import com.thoughtworks.ssr.infrastructure.persistence.project.converter.ProjectEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.project.repository.ProjectJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProjectRepositoryAdapter implements ProjectRepository {

    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectEntityConverter entityConverter;

    @Override
    public Project create(Project project) {
        var projectEntity = projectJpaRepository.saveAndFlush(entityConverter.from(project));
        return entityConverter.toDomain(projectEntity);
    }

    @Override
    public Project update(Project project) {
        var projectEntity = projectJpaRepository.save(entityConverter.from(project));
        return entityConverter.toDomain(projectEntity);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectJpaRepository.findById(id).map(entityConverter::toDomain);
    }

    @Override
    public Page<Project> pageProjects(Pageable pageable, Predicate predicate) {
        return projectJpaRepository.findAll(predicate, pageable)
                .map(entityConverter::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        projectJpaRepository.deleteById(id);
    }
}
