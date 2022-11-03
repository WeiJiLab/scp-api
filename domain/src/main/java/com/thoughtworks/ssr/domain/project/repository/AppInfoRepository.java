package com.thoughtworks.ssr.domain.project.repository;

import com.querydsl.core.types.Predicate;
import com.thoughtworks.ssr.domain.project.model.AppInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AppInfoRepository {
    AppInfo create(AppInfo application);

    AppInfo update(AppInfo application);

    Optional<AppInfo> findById(Long id);

    Page<AppInfo> pageAppInfo(Pageable pageable, Predicate predicate);

    void deleteById(Long id);
}
