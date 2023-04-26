package com.thoughtworks.ssr.domain.project.model;

import lombok.*;
import java.time.OffsetDateTime;

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
}
