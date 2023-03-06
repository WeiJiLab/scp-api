package com.thoughtworks.ssr.domain.imagescan.model;

import com.thoughtworks.ssr.domain.core.enums.ScanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageScanResult {
    private Long id;

    private Long projectId;

    private ScanType typeOption;

    private String sdlc;

    private int count;

    private int detailStatus;

    private String result;

    private String timeStamp;
}
