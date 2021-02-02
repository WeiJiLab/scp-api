package com.thoughtworks.security.scpapi.entity;

import com.thoughtworks.security.scpapi.controller.request.CreateSecurityToolRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityTool extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SecurityToolCategory category;

    private String description;

    private String metadata;

    public static SecurityTool from(CreateSecurityToolRequest createSecurityToolRequest) {
        return SecurityTool.builder()
                .name(createSecurityToolRequest.getName())
                .category(createSecurityToolRequest.getCategory())
                .description(createSecurityToolRequest.getDescription())
                .metadata(createSecurityToolRequest.getMetadata())
                .build();
    }
}
