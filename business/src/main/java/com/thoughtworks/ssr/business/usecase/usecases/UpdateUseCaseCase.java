package com.thoughtworks.ssr.business.usecase.usecases;

import com.thoughtworks.ssr.domain.usecase.model.UseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public class UpdateUseCaseCase {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "use_case_name_required")
        private String name;

        private String description;

        @NotNull(message = "security_tool_id_required")
        private Long securityToolId;

        @NotBlank(message = "script_path_required")
        private String scriptPath;
    }

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
