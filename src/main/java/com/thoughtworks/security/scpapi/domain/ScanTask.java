package com.thoughtworks.security.scpapi.domain;

import com.thoughtworks.security.scpapi.entity.Application;
import com.thoughtworks.security.scpapi.entity.SecurityToolEntity;
import com.thoughtworks.security.scpapi.enums.ScanTaskEnum;
import lombok.*;

import java.time.Instant;
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

    private Application application;

    private SecurityToolEntity securityToolEntity;

    private UseCase useCase;

    private ScanTaskEnum status;

    private Instant startTime;

    private Instant endTime;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
