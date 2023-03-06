package com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity;

import com.thoughtworks.ssr.domain.core.enums.ScanType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StopCommandEntity {
    private Long projectId;

    private ScanType typeOption;
}
