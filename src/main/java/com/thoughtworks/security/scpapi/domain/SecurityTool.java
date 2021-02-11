package com.thoughtworks.security.scpapi.domain;

import com.thoughtworks.security.scpapi.controller.request.CreateSecurityToolRequest;
import com.thoughtworks.security.scpapi.enums.SecurityToolCategory;
import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityTool {

    private Long id;

    private String name;

    private SecurityToolCategory category;

    private String description;

    private String metadata;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    public static SecurityTool from(CreateSecurityToolRequest request) {
        return SecurityTool.builder()
                .name(request.getName())
                .category(request.getCategory())
                .description(request.getDescription())
                .metadata(request.getMetadata())
                .build();
    }

}
