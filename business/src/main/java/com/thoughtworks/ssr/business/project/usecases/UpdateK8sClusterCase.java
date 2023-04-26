package com.thoughtworks.ssr.business.project.usecases;

import com.thoughtworks.ssr.domain.project.model.K8sCluster;
import com.thoughtworks.ssr.domain.project.model.Project;
import lombok.*;
import java.time.OffsetDateTime;

public class UpdateK8sClusterCase {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String name;

        private String kubeconfigContent;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private String kubeconfigContent;
        private Boolean connected;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public static Response from(K8sCluster project) {
            return Response.builder()
                    .id(project.getId())
                    .name(project.getName())
                    .kubeconfigContent(project.getKubeconfigContent())
                    .connected(project.getConnected())
                    .createdAt(project.getCreatedAt())
                    .updatedAt(project.getUpdatedAt())
                    .build();
        }
    }

}
