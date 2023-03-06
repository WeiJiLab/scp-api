package com.thoughtworks.ssr.domain.imagescan.model;

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
public class ImageScanReport {

    private Long id;

    private Long projectId;

    private String uuid;

    private String fileName;

    private String scanReportUrl;

    private String createAt;
}
