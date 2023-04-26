package com.thoughtworks.ssr.business.project.usecases;

import com.thoughtworks.ssr.domain.project.model.K8sCluster;
import com.thoughtworks.ssr.domain.project.model.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;

public class GetK8sClusterCase {

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

        public static Response from(K8sCluster kCls) {
            return Response.builder()
                    .id(kCls.getId())
                    .name(kCls.getName())
                    .kubeconfigContent(kCls.getKubeconfigContent())
                    .connected(kCls.getConnected())
                    .createdAt(kCls.getCreatedAt())
                    .updatedAt(kCls.getUpdatedAt())
                    .build();
        }
    }

}
