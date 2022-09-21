package com.thoughtworks.ssr.business.project.usecases;

import com.thoughtworks.ssr.domain.project.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

public class UpdateProjectCase {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String name;

        private String description;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private String description;
        private Long ownerId;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public static Response from(Project project) {
            return Response.builder()
                    .id(project.getId())
                    .name(project.getName())
                    .description(project.getName())
                    .ownerId(project.getOwnerId())
                    .createdAt(project.getCreatedAt())
                    .updatedAt(project.getUpdatedAt())
                    .build();
        }
    }

}
