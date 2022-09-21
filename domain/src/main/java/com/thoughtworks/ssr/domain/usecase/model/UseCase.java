package com.thoughtworks.ssr.domain.usecase.model;

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
public class UseCase {
    private Long id;
    private String name;
    private String description;
    private Long securityToolId;
    private String scriptPath;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
