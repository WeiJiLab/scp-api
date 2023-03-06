package com.thoughtworks.ssr.domain.imagescan.repository;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanReport;

public interface ImageScanReportRepository {
    ImageScanReport save(ImageScanReport imageScanReport);

    ImageScanReport findByProjectId(Long projectId);
}
