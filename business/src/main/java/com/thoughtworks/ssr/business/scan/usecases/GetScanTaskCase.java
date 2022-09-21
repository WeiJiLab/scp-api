package com.thoughtworks.ssr.business.scan.usecases;

import com.thoughtworks.ssr.domain.scan.enums.ScanTaskEnum;
import com.thoughtworks.ssr.domain.scan.model.ScanTask;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

public class GetScanTaskCase {

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private Long appId;
        private Long toolId;
        private Long useCaseId;
        private ScanTaskEnum status;
        private OffsetDateTime startTime;
        private OffsetDateTime endTime;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public static Response from(ScanTask scanTask) {
            return Response.builder()
                    .id(scanTask.getId())
                    .appId(scanTask.getAppId())
                    .toolId(scanTask.getToolId())
                    .useCaseId(scanTask.getUseCaseId())
                    .status(scanTask.getStatus())
                    .startTime(scanTask.getStartTime())
                    .endTime(scanTask.getEndTime())
                    .createdAt(scanTask.getCreatedAt())
                    .updatedAt(scanTask.getUpdatedAt())
                    .build();
        }
    }
}
