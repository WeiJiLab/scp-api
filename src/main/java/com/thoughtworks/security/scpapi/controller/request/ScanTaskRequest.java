package com.thoughtworks.security.scpapi.controller.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "启动合规检测需要携带的参数")
public class ScanTaskRequest {
    @NotNull
    private Integer appId;

    private List<Long> useCaseIds;
}
