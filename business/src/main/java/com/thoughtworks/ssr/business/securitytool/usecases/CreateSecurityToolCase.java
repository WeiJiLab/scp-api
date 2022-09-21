package com.thoughtworks.ssr.business.securitytool.usecases;

import com.thoughtworks.ssr.domain.securitytool.model.SecurityTool;
import com.thoughtworks.ssr.domain.securitytool.model.SecurityToolCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateSecurityToolCase {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        @NotBlank(message = "security_tool_name_required")
        private String name;

        @NotNull(message = "security_tool_category_required")
        private SecurityToolCategory category;

        @NotNull(message = "security_tool_description_required")
        private String description;

        @NotNull(message = "security_tool_metadata_required")
        private String metadata;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private SecurityToolCategory category;
        private String description;
        private String metadata;

        public static Response from(SecurityTool securityTool) {
            return Response.builder()
                    .id(securityTool.getId())
                    .name(securityTool.getName())
                    .category(securityTool.getCategory())
                    .description(securityTool.getName())
                    .metadata(securityTool.getMetadata())
                    .build();
        }
    }
}
