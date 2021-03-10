package com.thoughtworks.security.scpapi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EnvironmentTypePara {
    String dockerContainerId;
    String userName;
    String password;
    String addr;
}
