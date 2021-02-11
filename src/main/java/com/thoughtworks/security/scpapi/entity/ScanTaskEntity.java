package com.thoughtworks.security.scpapi.entity;

import com.thoughtworks.security.scpapi.enums.ScanTaskEnum;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "scan_task")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScanTaskEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long appId;

    private Long toolId;

    private Long useCaseId;

    @Enumerated(value = EnumType.STRING)
    private ScanTaskEnum status;

    private Instant startTime;

    private Instant endTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appId", referencedColumnName = "id", insertable = false, updatable = false)
    private Application application;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "toolId", referencedColumnName = "id", insertable = false, updatable = false)
    private SecurityToolEntity securityToolEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "useCaseId", referencedColumnName = "id", insertable = false, updatable = false)
    private UseCaseEntity useCase;

}
