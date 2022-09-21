package com.thoughtworks.ssr.business.usecase.usecases;

import com.thoughtworks.ssr.domain.usecase.model.UseCaseGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

public class GetUseCaseGroupCase {
    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private String description;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public static Response from(UseCaseGroup useCaseGroup) {
            return Response.builder()
                    .id(useCaseGroup.getId())
                    .name(useCaseGroup.getName())
                    .description(useCaseGroup.getName())
                    .createdAt(useCaseGroup.getCreatedAt())
                    .updatedAt(useCaseGroup.getUpdatedAt())
                    .build();
        }
    }
}
