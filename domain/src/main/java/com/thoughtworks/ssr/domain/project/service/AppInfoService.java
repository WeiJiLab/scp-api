package com.thoughtworks.ssr.domain.project.service;

import com.thoughtworks.ssr.domain.project.exception.AppInfoException;
import com.thoughtworks.ssr.domain.project.model.AppInfo;
import com.thoughtworks.ssr.domain.project.query.AppInfoQuery;
import com.thoughtworks.ssr.domain.project.repository.AppInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppInfoService {
    private final AppInfoRepository applicationRepository;

    @Transactional
    public AppInfo addAppInfo(AppInfo appInfo) {
        return applicationRepository.create(appInfo);
    }

    @Transactional
    public AppInfo updateAppInfo(AppInfo application) {
        return applicationRepository.update(application);
    }

    public AppInfo findById(Long id) {
        return applicationRepository.findById(id).orElseThrow(AppInfoException::notFound);
    }

    public Page<AppInfo> pageAppInfo(AppInfoQuery appInfoQuery, Pageable pageable) {
        return applicationRepository.pageAppInfo(appInfoQuery, pageable);
    }

    public void deleteById(Long id) {
        try {
            applicationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw AppInfoException.notFound();
        }
    }
}
