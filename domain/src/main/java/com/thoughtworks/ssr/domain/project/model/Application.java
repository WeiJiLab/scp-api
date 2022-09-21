package com.thoughtworks.ssr.domain.project.model;

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
public class Application {
    private Long id;
    private Long projectId;
    private String name;
    private String description;
    private Long ownerId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
