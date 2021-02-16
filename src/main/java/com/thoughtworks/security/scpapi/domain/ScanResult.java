package com.thoughtworks.security.scpapi.domain;

import com.thoughtworks.security.scpapi.enums.ScanResultEnum;
import lombok.*;

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

    private String useCaseName;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
}
