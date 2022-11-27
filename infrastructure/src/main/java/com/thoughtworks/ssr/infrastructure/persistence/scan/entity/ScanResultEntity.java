package com.thoughtworks.ssr.infrastructure.persistence.scan.entity;

import com.thoughtworks.ssr.domain.scan.enums.ScanResultEnum;
import com.thoughtworks.ssr.infrastructure.persistence.common.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
}

