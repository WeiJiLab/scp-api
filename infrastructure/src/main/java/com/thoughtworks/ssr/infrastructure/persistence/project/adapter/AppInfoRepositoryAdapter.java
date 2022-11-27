package com.thoughtworks.ssr.infrastructure.persistence.project.adapter;

import com.thoughtworks.ssr.domain.project.model.AppInfo;
import com.thoughtworks.ssr.domain.project.query.AppInfoQuery;
import com.thoughtworks.ssr.domain.project.repository.AppInfoRepository;
import com.thoughtworks.ssr.infrastructure.persistence.project.converter.AppInfoEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.project.entity.AppInfoEntity;
import com.thoughtworks.ssr.infrastructure.persistence.project.repository.AppInfoJpaRepository;
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
public class AppInfoRepositoryAdapter implements AppInfoRepository {

    private final AppInfoEntityConverter converter;
    private final AppInfoJpaRepository jpaRepository;

    @Override
    public AppInfo create(AppInfo appInfo) {
        var appInfoEntity = jpaRepository.saveAndFlush(converter.from(appInfo));
        return converter.toDomain(appInfoEntity);
    }

    @Override
    public AppInfo update(AppInfo appInfo) {
        var appInfoEntity = jpaRepository.save(converter.from(appInfo));
        return converter.toDomain(appInfoEntity);
    }

    @Override
    public Optional<AppInfo> findById(Long id) {
        return jpaRepository.findById(id).map(converter::toDomain);
    }

    @Override
    public Page<AppInfo> pageAppInfo(AppInfoQuery appInfoQuery, Pageable pageable) {

        var appInfoSpecifications = buildSpecifications(appInfoQuery);

        return jpaRepository.findAll(appInfoSpecifications, pageable).map(converter::toDomain);
    }

    private Specification<AppInfoEntity> buildSpecifications(AppInfoQuery appInfoQuery) {
        return (root, query, criteriaBuilder) -> {

            var predicates = new LinkedList<Predicate>();

            if (!ObjectUtils.isEmpty(appInfoQuery.name())) {
                predicates.add(criteriaBuilder.equal(root.get(AppInfoEntity.Fields.name), appInfoQuery.name()));
            }

            if (!ObjectUtils.isEmpty(appInfoQuery.username())) {
                predicates.add(criteriaBuilder.like(root.get(AppInfoEntity.Fields.username), "%" + appInfoQuery.username() + "%"));
            }

            if (!ObjectUtils.isEmpty(appInfoQuery.repoType())) {
                predicates.add(criteriaBuilder.equal(root.get(AppInfoEntity.Fields.repoType), appInfoQuery.repoType()));
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
        jpaRepository.deleteById(id);
    }
}
