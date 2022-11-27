package com.thoughtworks.ssr.business.project.usecases;

import com.thoughtworks.ssr.domain.core.enums.RepoType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateAppInfoCase {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull(message = "project_id_required")
        private Long projectId;

        @NotBlank(message = "project_name_required")
        private String name;

        private String description;

//        @NotBlank(message = "repo_name_required")
        private String repo;

        private String branch = "master";

        private String username;

        private String password;

        private RepoType repoType = RepoType.GIT;

        private String codePath = "/";
    }

}
