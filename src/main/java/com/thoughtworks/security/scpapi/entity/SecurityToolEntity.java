package com.thoughtworks.security.scpapi.entity;

import com.thoughtworks.security.scpapi.domain.SecurityTool;
import com.thoughtworks.security.scpapi.enums.SecurityToolCategory;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityToolEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SecurityToolCategory category;

    private String description;

    private String metadata;

    public static SecurityTool toSecurityTool(SecurityToolEntity securityToolEntity) {
        return SecurityTool
                .builder()
                .id(securityToolEntity.getId())
                .name(securityToolEntity.getName())
                .category(securityToolEntity.getCategory())
                .description(securityToolEntity.getDescription())
                .metadata(securityToolEntity.getMetadata())
                .createdAt(securityToolEntity.getCreatedAt())
                .updatedAt(securityToolEntity.getUpdatedAt())
                .build();

    }

    public static List<SecurityTool> toSecurityTools(List<SecurityToolEntity> securityToolEntities) {
        return securityToolEntities.stream()
                .map(SecurityToolEntity::toSecurityTool)
                .collect(Collectors.toList());
    }
}
