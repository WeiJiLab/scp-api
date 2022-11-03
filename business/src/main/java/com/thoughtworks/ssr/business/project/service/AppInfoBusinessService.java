package com.thoughtworks.ssr.business.project.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.thoughtworks.ssr.business.project.usecases.CreateAppInfoCase;
import com.thoughtworks.ssr.business.project.usecases.UpdateAppInfoCase;
import com.thoughtworks.ssr.common.serviceity.AESCrypt;
import com.thoughtworks.ssr.domain.project.model.AppInfo;
import com.thoughtworks.ssr.domain.project.service.AppInfoService;
import com.thoughtworks.ssr.domain.user.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.thoughtworks.ssr.infrastructure.persistence.project.entity.QAppInfoEntity.appInfoEntity;

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

    public Page<AppInfo> pageAppInfo(Pageable pageable, String name, String username) {

        var predicate = buildPredicate(name, username);

        return appInfoService.pageAppInfo(pageable, predicate);
    }

    public Predicate buildPredicate(String name,  String username) {

        var predicate = new BooleanBuilder(null);

        if (!ObjectUtils.isEmpty(name)) {
            predicate = new BooleanBuilder(predicate).and(appInfoEntity.name.containsIgnoreCase(name));
        }

        if (!ObjectUtils.isEmpty(username)) {
            predicate = new BooleanBuilder(predicate).and(appInfoEntity.username.containsIgnoreCase(username));
        }

        return predicate;
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
