package com.thoughtworks.ssr.domain.securitytool.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
