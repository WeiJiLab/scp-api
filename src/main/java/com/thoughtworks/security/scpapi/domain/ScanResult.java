package com.thoughtworks.security.scpapi.domain;

import com.thoughtworks.security.scpapi.entity.AuditModel;
import com.thoughtworks.security.scpapi.entity.ScanTaskEntity;
import com.thoughtworks.security.scpapi.util.ScanResultEnum;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
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

