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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "image_scan_steps")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ImageScanStageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pj_id")
    @NotNull
    private Long projectId;

    @Column(name = "type_option")
    private ScanType typeOption;

    private int status;

    private int step;

    private String stage;

    private String logs;

    @Column(name = "time_stamp")
    private String timeStamp;
}
