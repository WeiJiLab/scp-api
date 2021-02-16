package com.thoughtworks.security.scpapi.controller.request;

import com.thoughtworks.security.scpapi.enums.ScanTaskEnum;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearchRequest {

    private Long appId;
    private Long toolId;
    private Long projectId;
    private Long useCaseId;

    private ScanTaskEnum status;
}
