package com.thoughtworks.ssr.domain.scan.model;

import com.thoughtworks.ssr.domain.scan.enums.ScanTaskEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScanTask {
    private Long id;
    private Long appId;
    private Long toolId;
    private Long useCaseId;
    private ScanTaskEnum status;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
