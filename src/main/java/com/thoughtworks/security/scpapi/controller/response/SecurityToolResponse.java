package com.thoughtworks.security.scpapi.controller.response;

import com.thoughtworks.security.scpapi.domain.SecurityTool;
import com.thoughtworks.security.scpapi.enums.SecurityToolCategory;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecurityToolResponse {

    private Long id;

    private String name;

    private SecurityToolCategory category;

    private String description;

    private String metadata;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    public static SecurityToolResponse fromSecurityTool(SecurityTool securityTool) {
        return SecurityToolResponse
                .builder()
                .id(securityTool.getId())
                .name(securityTool.getName())
                .category(securityTool.getCategory())
                .description(securityTool.getDescription())
                .metadata(securityTool.getMetadata())
                .createdAt(securityTool.getCreatedAt())
                .updatedAt(securityTool.getUpdatedAt())
                .build();
    }

    public static List<SecurityToolResponse> fromSecurityTools(List<SecurityTool> securityTools) {
        return securityTools.stream()
                .map(SecurityToolResponse::fromSecurityTool)
                .collect(Collectors.toList());
    }
}
