package com.thoughtworks.security.scpapi.domain;

import com.thoughtworks.security.scpapi.entity.AuditModel;
import com.thoughtworks.security.scpapi.util.ScanResultEnum;
import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScanResult extends AuditModel {

    private Long id;

    private Integer scanTaskId;

    private ScanResultEnum result;

    private String resultPath;

    private String useCaseName;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
