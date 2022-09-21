package com.thoughtworks.ssr.business.usecase.usecases;

import com.thoughtworks.ssr.domain.usecase.model.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

public class GetUseCaseCase {
    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private String description;
        private Long securityToolId;
        private String scriptPath;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public static Response from(UseCase useCase) {

            return Response.builder()
                    .id(useCase.getId())
                    .name(useCase.getName())
                    .description(useCase.getName())
                    .securityToolId(useCase.getSecurityToolId())
                    .scriptPath(useCase.getScriptPath())
                    .createdAt(useCase.getCreatedAt())
                    .updatedAt(useCase.getUpdatedAt())
                    .build();
        }
    }
}
