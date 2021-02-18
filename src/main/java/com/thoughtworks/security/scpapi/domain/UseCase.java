package com.thoughtworks.security.scpapi.domain;

import com.thoughtworks.security.scpapi.entity.SecurityToolEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UseCase {

    private Long id;

    private String name;

    private String description;

    private Long securityToolId;

    private String scriptPath;

    private SecurityToolEntity securityToolEntity;

}
