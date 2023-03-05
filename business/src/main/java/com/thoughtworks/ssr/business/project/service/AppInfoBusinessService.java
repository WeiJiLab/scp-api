package com.thoughtworks.ssr.business.project.service;

import com.thoughtworks.ssr.business.project.usecases.CreateAppInfoCase;
import com.thoughtworks.ssr.business.project.usecases.UpdateAppInfoCase;
import com.thoughtworks.ssr.common.serviceity.AESCrypt;
import com.thoughtworks.ssr.domain.project.model.AppInfo;
import com.thoughtworks.ssr.domain.project.query.AppInfoQuery;
import com.thoughtworks.ssr.domain.project.service.AppInfoService;
import com.thoughtworks.ssr.domain.iam.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class AppInfoBusinessService {
    private final AppInfoService appInfoService;

    public Long create(CreateAppInfoCase.Request request, CustomUserDetails userDetails) {
        var appInfo = AppInfo.builder()
                .projectId(request.getProjectId())
                .name(request.getName())
                .description(request.getDescription())
                .repo(request.getRepo())
                .branch(request.getBranch())
                .username(request.getUsername())
                .password(AESCrypt.encrypt(request.getPassword()))
                .repoType(request.getRepoType())
                .codePath(request.getCodePath())
                .build();
        return appInfoService.addAppInfo(appInfo).getId();
    }

    public AppInfo findById(Long id) {
        return appInfoService.findById(id);
    }

    public Page<AppInfo> pageAppInfo(AppInfoQuery appInfoQuery, Pageable pageable) {
        return appInfoService.pageAppInfo(appInfoQuery, pageable);
    }


    public void deleteById(Long id) {
        appInfoService.deleteById(id);
    }

    public void updateAppInfo(Long id, UpdateAppInfoCase.Request request) {
        var appInfo = appInfoService.findById(id);

        if (!ObjectUtils.isEmpty(request.getName())) {
            appInfo.setName(request.getName());
        }

        if (!ObjectUtils.isEmpty(request.getDescription())) {
            appInfo.setDescription(request.getDescription());
        }
        if (!ObjectUtils.isEmpty(request.getRepo())) {
            appInfo.setRepo(request.getRepo());
        }
        if (!ObjectUtils.isEmpty(request.getBranch())) {
            appInfo.setBranch(request.getBranch());
        }
        if (!ObjectUtils.isEmpty(request.getUsername())) {
            appInfo.setUsername(request.getUsername());
        }
        if (!ObjectUtils.isEmpty(request.getPassword())) {
            appInfo.setPassword(AESCrypt.encrypt(request.getPassword()));
        }
        if (!ObjectUtils.isEmpty(request.getRepoType())) {
            appInfo.setRepoType(request.getRepoType());
        }
        if (!ObjectUtils.isEmpty(request.getCodePath())) {
            appInfo.setCodePath(request.getCodePath());
        }

        appInfoService.updateAppInfo(appInfo);
    }
}
