package com.thoughtworks.ssr.domain.project.query;

import com.thoughtworks.ssr.domain.core.enums.RepoType;

public record AppInfoQuery(
        String name,
        String username,
        RepoType repoType
) {
}
