package com.thoughtworks.ssr.business.scan.usecases;

import com.thoughtworks.ssr.domain.scan.enums.ScanResultEnum;
import com.thoughtworks.ssr.domain.scan.model.ScanResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

public class GetScanResultCase {

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private Long scanTaskId;
        private ScanResultEnum result;
        private String resultPath;
        private Long useCaseId;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public static Response from(ScanResult scanResult) {
            return Response.builder()
                    .id(scanResult.getId())
                    .scanTaskId(scanResult.getScanTaskId())
                    .result(scanResult.getResult())
                    .resultPath(scanResult.getResultPath())
                    .useCaseId(scanResult.getUseCaseId())
                    .createdAt(scanResult.getCreatedAt())
                    .updatedAt(scanResult.getUpdatedAt())
                    .build();
        }
    }
}
