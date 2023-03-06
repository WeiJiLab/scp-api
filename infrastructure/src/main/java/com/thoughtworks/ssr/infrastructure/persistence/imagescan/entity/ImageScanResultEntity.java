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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Table(name = "image_scan_result")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class ImageScanResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pj_id")
    @NotNull
    private Long projectId;

    @Column(name = "type_option")
    private ScanType typeOption;

    private String sdlc;

    @Column(name = "res_count")
    private int count;

    @Column(name = "detail_status")
    private int detailStatus;

    private String result;

    @Column(name = "time_stamp")
    private String timeStamp;
}
