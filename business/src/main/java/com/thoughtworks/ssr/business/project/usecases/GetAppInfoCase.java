package com.thoughtworks.ssr.business.project.usecases;

import com.thoughtworks.ssr.domain.core.enums.RepoType;
import com.thoughtworks.ssr.domain.project.model.AppInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

public class GetAppInfoCase {

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private Long projectId;
        private String name;
        private String description;
        private String repo;
        private String branch;
        private String username;
        private RepoType repoType;
        private String codePath;
        private String workdir;

        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public static Response from(AppInfo appInfo) {
            return Response.builder()
                    .id(appInfo.getId())
                    .projectId(appInfo.getProjectId())
                    .name(appInfo.getName())
                    .description(appInfo.getDescription())
                    .repo(appInfo.getRepo())
                    .branch(appInfo.getBranch())
                    .username(appInfo.getUsername())
                    .repoType(appInfo.getRepoType())
                    .codePath(appInfo.getCodePath())
                    .createdAt(appInfo.getCreatedAt())
                    .updatedAt(appInfo.getUpdatedAt())
                    .build();
        }
    }

}
