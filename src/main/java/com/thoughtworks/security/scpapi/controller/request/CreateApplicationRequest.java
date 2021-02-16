package com.thoughtworks.security.scpapi.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplicationRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Long ownerId;
}
