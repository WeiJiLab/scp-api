package com.thoughtworks.ssr.infrastructure.persistence.project.adapter;

import com.thoughtworks.ssr.domain.project.model.Project;
import com.thoughtworks.ssr.domain.project.query.ProjectQuery;
import com.thoughtworks.ssr.domain.project.repository.ProjectRepository;
import com.thoughtworks.ssr.infrastructure.persistence.project.converter.ProjectEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.project.entity.ProjectEntity;
import com.thoughtworks.ssr.infrastructure.persistence.project.repository.ProjectJpaRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.LinkedList;
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
    public Page<Project> pageProjects(ProjectQuery projectQuery, Pageable pageable) {

        var projectSpecifications = buildSpecifications(projectQuery);
        return projectJpaRepository.findAll(projectSpecifications, pageable).map(entityConverter::toDomain);
    }

    private Specification<ProjectEntity> buildSpecifications(ProjectQuery projectQuery) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new LinkedList<Predicate>();
            if (!ObjectUtils.isEmpty(projectQuery.name())) {
                predicates.add(criteriaBuilder.like(root.get(ProjectEntity.Fields.name), projectQuery.name()));
            }

            if (!ObjectUtils.isEmpty(projectQuery.ownerId())) {
                predicates.add(criteriaBuilder.equal(root.get(ProjectEntity.Fields.ownerId), projectQuery.ownerId()));
            }

            if (predicates.isEmpty()) {
                return null;
            }

            query.where(predicates.toArray(new Predicate[0]));
            return query.getRestriction();
        };
    }

    @Override
    public void deleteById(Long id) {
        projectJpaRepository.deleteById(id);
    }
}
