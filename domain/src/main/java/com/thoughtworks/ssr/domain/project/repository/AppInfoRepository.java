package com.thoughtworks.ssr.domain.project.repository;

import com.thoughtworks.ssr.domain.project.model.AppInfo;
import com.thoughtworks.ssr.domain.project.query.AppInfoQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AppInfoRepository {
    AppInfo create(AppInfo application);

    AppInfo update(AppInfo application);

    Optional<AppInfo> findById(Long id);

    Page<AppInfo> pageAppInfo(AppInfoQuery appInfoQuery,Pageable pageable );

    void deleteById(Long id);
}
