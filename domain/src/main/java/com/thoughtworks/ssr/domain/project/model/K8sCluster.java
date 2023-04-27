package com.thoughtworks.ssr.domain.project.model;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.io.StringReader;
import java.time.OffsetDateTime;

@Slf4j
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class K8sCluster {
    private Long id;
    private Long projectId;
    private String name;
    private String kubeconfigContent;
    private Boolean connected;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public void testConnection() {
        try {
            KubeConfig kubeConfig = KubeConfig.loadKubeConfig(new StringReader(kubeconfigContent));
            ApiClient client = Config.fromConfig(kubeConfig);
            new CoreV1Api(client).listNamespacedPod(kubeConfig.getNamespace(), null, null, null, null, null, null, null, null, null, null);
            this.connected = true;
        } catch (ApiException | IOException e) {
            log.error("Failed to connect to k8s cluster");
            log.debug("Failed to connect to k8s cluster: ", e);
            this.connected = false;
        }
    }
}
