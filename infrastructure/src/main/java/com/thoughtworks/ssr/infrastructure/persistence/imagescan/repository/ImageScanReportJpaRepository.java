package com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImageScanReportJpaRepository extends
        JpaRepository<ImageScanReportEntity, Long>,
        JpaSpecificationExecutor<ImageScanReportEntity> {
    ImageScanReportEntity findByProjectId(Long projectId);
}
