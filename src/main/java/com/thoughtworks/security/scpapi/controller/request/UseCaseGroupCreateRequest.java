package com.thoughtworks.security.scpapi.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UseCaseGroupCreateRequest {

    @NotBlank
    private String name;

    private String description;
}
