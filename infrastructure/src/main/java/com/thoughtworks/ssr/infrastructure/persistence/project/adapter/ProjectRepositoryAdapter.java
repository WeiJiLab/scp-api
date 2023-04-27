package com.thoughtworks.ssr.infrastructure.persistence.project.adapter;

import com.thoughtworks.ssr.domain.project.model.K8sCluster;
import com.thoughtworks.ssr.domain.project.model.Project;
import com.thoughtworks.ssr.domain.project.query.ProjectQuery;
import com.thoughtworks.ssr.domain.project.repository.ProjectRepository;
import com.thoughtworks.ssr.infrastructure.persistence.project.converter.K8sClusterEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.project.converter.ProjectEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.project.entity.K8sClusterEntity;
import com.thoughtworks.ssr.infrastructure.persistence.project.entity.ProjectEntity;
import com.thoughtworks.ssr.infrastructure.persistence.project.repository.K8sClusterJpaRepository;
import com.thoughtworks.ssr.infrastructure.persistence.project.repository.ProjectJpaRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectRepositoryAdapter implements ProjectRepository {

    private final ProjectJpaRepository projectJpaRepository;
    private final K8sClusterJpaRepository k8sClusterJpaRepository;
    private final ProjectEntityConverter projectEntityConverter;
    private final K8sClusterEntityConverter k8sClusterEntityConverter;

    @Override
    public Project create(Project project) {
        var projectEntity = projectJpaRepository.saveAndFlush(projectEntityConverter.from(project));
        return projectEntityConverter.toDomain(projectEntity);
    }

    @Override
    public Project update(Project project) {
        var projectEntity = projectJpaRepository.save(projectEntityConverter.from(project));
        return projectEntityConverter.toDomain(projectEntity);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectJpaRepository.findById(id)
                .map(projectEntityConverter::toDomain)
                .map(p -> {
                    List<K8sCluster> allCls = k8sClusterJpaRepository.findAll(
                            (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(K8sClusterEntity.Fields.projectId), id))
                            .stream().map(k8sClusterEntityConverter::toDomain).toList();
                    p.setK8sClusters(allCls);
                    return p;
                });
    }

    @Override
    public Page<Project> pageProjects(ProjectQuery projectQuery, Pageable pageable) {

        var projectSpecifications = buildSpecifications(projectQuery);
        return projectJpaRepository.findAll(projectSpecifications, pageable).map(projectEntityConverter::toDomain);
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
        k8sClusterJpaRepository.delete((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(K8sClusterEntity.Fields.projectId), id));
    }
}
