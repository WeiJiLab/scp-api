package com.thoughtworks.security.scpapi.dto;

import io.swagger.annotations.ApiModel;




@ApiModel(value = "合规检测用例录入时需要携带的参数")
public class ComplianceUseCaseInputDto extends ComplianceUseCaseDto{
    private String useCaseUrl;
    private Integer toolId;
    public ComplianceUseCaseInputDto(Long id, String description, String name,
                                     String useCaseUrl, Integer toolId) {
        super(id, description, name, toolId);
        this.useCaseUrl = useCaseUrl;
    }

    public String getUseCaseUrl() {
        return useCaseUrl;
    }

    public void setUseCaseUrl(String useCaseUrl) {
        this.useCaseUrl = useCaseUrl;
    }
}
