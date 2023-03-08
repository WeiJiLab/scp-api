package com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity;

import com.thoughtworks.ssr.domain.core.enums.ScanType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Table(name = "image_scan_job")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@FieldNameConstants
public class ImageScanCommandEntity {

    @Id
    @Column(name = "pj_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(name = "pj_name")
//    @NotNull(message = "project name cannot be null")
    private String projectName;

    @Column(name = "type_option")
    @NotNull(message = "type option must be provided")
    private ScanType typeOption;

    @Column(name = "create_time")
    private String createTime;
}


