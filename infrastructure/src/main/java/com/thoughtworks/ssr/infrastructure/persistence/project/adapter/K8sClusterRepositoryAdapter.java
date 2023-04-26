package com.thoughtworks.ssr.infrastructure.persistence.project.adapter;

import com.thoughtworks.ssr.domain.project.model.K8sCluster;
import com.thoughtworks.ssr.domain.project.query.K8sClusterQuery;
import com.thoughtworks.ssr.domain.project.repository.K8sClusterRepository;
import com.thoughtworks.ssr.infrastructure.persistence.project.converter.K8sClusterEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.project.entity.K8sClusterEntity;
import com.thoughtworks.ssr.infrastructure.persistence.project.entity.ProjectEntity;
import com.thoughtworks.ssr.infrastructure.persistence.project.repository.K8sClusterJpaRepository;
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
public class K8sClusterRepositoryAdapter implements K8sClusterRepository {

    private final K8sClusterJpaRepository kClsJpaRepository;
    private final K8sClusterEntityConverter entityConverter;

    @Override
    public K8sCluster create(K8sCluster kCls) {
        var kClsEntity = kClsJpaRepository.saveAndFlush(entityConverter.from(kCls));
        return entityConverter.toDomain(kClsEntity);
    }

    @Override
    public K8sCluster update(K8sCluster kCls) {
        var projectEntity = kClsJpaRepository.save(entityConverter.from(kCls));
        return entityConverter.toDomain(projectEntity);
    }

    @Override
    public Optional<K8sCluster> findById(Long id) {
        return kClsJpaRepository.findById(id).map(entityConverter::toDomain);
    }

    @Override
    public Page<K8sCluster> pageClusters(K8sClusterQuery projectQuery, Pageable pageable) {

        var projectSpecifications = buildSpecifications(projectQuery);
        return kClsJpaRepository.findAll(projectSpecifications, pageable).map(entityConverter::toDomain);
    }

    private Specification<K8sClusterEntity> buildSpecifications(K8sClusterQuery k8sClusterQuery) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new LinkedList<Predicate>();
            predicates.add(criteriaBuilder.equal(root.get(K8sClusterEntity.Fields.projectId), k8sClusterQuery.projectId()));

            if (!ObjectUtils.isEmpty(k8sClusterQuery.name())) {
                predicates.add(criteriaBuilder.like(root.get(ProjectEntity.Fields.name), k8sClusterQuery.name()));
            }

            query.where(predicates.toArray(new Predicate[0]));
            return query.getRestriction();
        };
    }

    @Override
    public void deleteById(Long id) {
        kClsJpaRepository.deleteById(id);
    }
}
