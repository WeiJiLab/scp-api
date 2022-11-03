package com.thoughtworks.ssr.infrastructure.persistence.project.adapter;

import com.querydsl.core.types.Predicate;
import com.thoughtworks.ssr.domain.project.model.AppInfo;
import com.thoughtworks.ssr.domain.project.repository.AppInfoRepository;
import com.thoughtworks.ssr.infrastructure.persistence.project.converter.AppInfoEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.project.repository.AppInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

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
    public Page<AppInfo> pageAppInfo(Pageable pageable, Predicate predicate) {
        return jpaRepository.findAll(predicate, pageable)
                .map(converter::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
