package com.thoughtworks.ssr.business.project.usecases;

import com.thoughtworks.ssr.domain.core.enums.RepoType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class CreateAppInfoCase {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "project_id_required")
        private Long projectId;

        @NotBlank(message = "project_name_required")
        private String name;

        private String description;

        @NotBlank(message = "repo_name_required")
        private String repo;

        @NotBlank(message = "repo_name_required")
        private String branch;

        @NotBlank(message = "username_required")
        private String username;

        @NotBlank(message = "password_required")
        private String password;

        @NotBlank(message = "repo_type_required")
        private RepoType repoType;

        private String codePath;
    }

}
