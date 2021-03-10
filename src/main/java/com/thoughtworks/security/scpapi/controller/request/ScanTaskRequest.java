package com.thoughtworks.security.scpapi.controller.request;

import com.thoughtworks.security.scpapi.enums.EnvironmentType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "启动合规检测需要携带的参数")
public class ScanTaskRequest {
    @NotNull
    private Long appId;

    private List<Long> useCaseIds;

    private EnvironmentType environmentType;

    private String userName;

    private String password;

    private String dockerContainerId;

    private String addr;
}
