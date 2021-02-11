package com.thoughtworks.security.scpapi.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@ApiModel(value = "合规检测用例信息")
@Builder
@AllArgsConstructor
public class ComplianceUseCaseDto {
    @ApiModelProperty(value = "id", name = "usecaseId")
    private Long id;
    private String description;
    private String name;
    private Long toolId;
}
