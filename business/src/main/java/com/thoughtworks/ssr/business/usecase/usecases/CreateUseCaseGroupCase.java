package com.thoughtworks.ssr.business.usecase.usecases;

import com.thoughtworks.ssr.domain.usecase.model.UseCaseGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

public class CreateUseCaseGroupCase {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "use_case_group_name_required")
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

        public static Response from(UseCaseGroup useCaseGroup) {
            return Response.builder()
                    .id(useCaseGroup.getId())
                    .name(useCaseGroup.getName())
                    .description(useCaseGroup.getName())
                    .build();
        }
    }
}
