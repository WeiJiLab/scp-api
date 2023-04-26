package com.thoughtworks.ssr.domain.project.query;

public record K8sClusterQuery(
        long projectId,
        String name
) {
}
