package com.thoughtworks.security.scpapi.controller.request;

import com.thoughtworks.security.scpapi.enums.SecurityToolCategory;
import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSecurityToolRequest {

    @NotNull
    private String name;

    @NotNull
    private SecurityToolCategory category;

    @NotNull
    private String description;

    @NotNull
    private String metadata;
}
