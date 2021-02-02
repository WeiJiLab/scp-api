package com.thoughtworks.security.scpapi.controller.request;

import com.thoughtworks.security.scpapi.entity.SecurityToolCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
