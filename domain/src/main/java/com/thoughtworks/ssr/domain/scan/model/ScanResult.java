package com.thoughtworks.ssr.domain.scan.model;

import com.thoughtworks.ssr.domain.scan.enums.ScanResultEnum;
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
public class ScanResult {
    private Long id;
    private Long scanTaskId;
    private ScanResultEnum result;
    private String resultPath;
    private Long useCaseId;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}

