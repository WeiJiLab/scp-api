package com.thoughtworks.ssr.business.project.usecases;

import com.thoughtworks.ssr.domain.project.model.K8sCluster;
import com.thoughtworks.ssr.domain.project.model.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

public class GetProjectCase {

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private String description;
        private Long ownerId;
        private List<K8sCluster> k8sClusters;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public static Response from(Project project) {
            return Response.builder()
                    .id(project.getId())
                    .name(project.getName())
                    .description(project.getDescription())
                    .ownerId(project.getOwnerId())
                    .k8sClusters(project.getK8sClusters())
                    .createdAt(project.getCreatedAt())
                    .updatedAt(project.getUpdatedAt())
                    .build();
        }
    }

}
