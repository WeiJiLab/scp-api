package com.thoughtworks.ssr.business.project.usecases;

import com.thoughtworks.ssr.domain.project.model.K8sCluster;
import com.thoughtworks.ssr.domain.project.model.Project;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class CreateK8sClusterCase {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "k8s_cluster_name_required")
        private String name;

        @NotBlank(message = "k8s_cluster_kubeconfig_content_required")
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

        public static Response from(K8sCluster kCls) {
            return Response.builder()
                    .id(kCls.getId())
                    .name(kCls.getName())
                    .kubeconfigContent(kCls.getKubeconfigContent())
                    .connected(kCls.getConnected())
                    .build();
        }
    }

}
