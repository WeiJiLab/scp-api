package com.thoughtworks.security.scpapi.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.security.Timestamp;

@Data
@ApiModel(value = "合规检测用例结果")
public class ComplianceScanResultDto {
    private Long result;
    private Timestamp create_time;
    private String create_by;
    private Timestamp update_time;
}
