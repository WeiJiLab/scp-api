package com.thoughtworks.ssr.business.securitytool.usecases;

import com.thoughtworks.ssr.domain.securitytool.model.SecurityTool;
import com.thoughtworks.ssr.domain.securitytool.model.SecurityToolCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

public class GetSecurityToolCase {

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private SecurityToolCategory category;
        private String description;
        private String metadata;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public static Response from(SecurityTool securityTool) {
            return Response.builder()
                    .id(securityTool.getId())
                    .name(securityTool.getName())
                    .category(securityTool.getCategory())
                    .description(securityTool.getName())
                    .metadata(securityTool.getMetadata())
                    .createdAt(securityTool.getCreatedAt())
                    .updatedAt(securityTool.getUpdatedAt())
                    .build();
        }
    }

}
