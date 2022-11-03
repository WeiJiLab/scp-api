package com.thoughtworks.ssr.business.project.usecases;

import com.thoughtworks.ssr.domain.core.enums.RepoType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UpdateAppInfoCase {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private String name;

        private String description;

        private String repo;

        private String branch;

        private String username;

        private String password;

        private RepoType repoType;

        private String codePath;
    }

}
