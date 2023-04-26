package com.thoughtworks.ssr.infrastructure.persistence.project.entity;

import com.thoughtworks.ssr.infrastructure.persistence.common.AuditModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Table(name = "k8s_cluster")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class K8sClusterEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    private String name;

    private String kubeconfigContent;

    private Boolean connected;
}
