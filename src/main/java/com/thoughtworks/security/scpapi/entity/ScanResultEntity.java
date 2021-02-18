package com.thoughtworks.security.scpapi.entity;

import com.thoughtworks.security.scpapi.enums.ScanResultEnum;
import lombok.*;

import javax.persistence.*;

@Entity(name = "scan_result")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScanResultEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long scanTaskId;

    private ScanResultEnum result;

    private String resultPath;

    private Long useCaseId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scanTaskId", referencedColumnName = "id", insertable = false, updatable = false)
    private ScanTaskEntity scanTask;

}

