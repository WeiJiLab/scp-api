package com.thoughtworks.security.scpapi.controller.request;

import com.thoughtworks.security.scpapi.enums.ScanTaskEnum;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearchRequest {

    private Integer appId;
    private Integer toolId;
    private Integer projectId;
    private Long useCaseId;

    private ScanTaskEnum status;
}
