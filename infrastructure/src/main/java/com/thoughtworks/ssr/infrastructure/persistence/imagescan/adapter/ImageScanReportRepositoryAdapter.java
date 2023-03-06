package com.thoughtworks.ssr.infrastructure.persistence.imagescan.adapter;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanReport;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanReportRepository;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.converter.ImageScanReportEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository.ImageScanReportJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageScanReportRepositoryAdapter implements ImageScanReportRepository {

    private final ImageScanReportEntityConverter converter;

    private final ImageScanReportJpaRepository jpaRepository;

    @Override
    public ImageScanReport save(ImageScanReport imageScanReport) {
        var imageScanReportEntity = jpaRepository.save(converter.from(imageScanReport));
        return converter.toDomain(imageScanReportEntity);
    }

    @Override
    public ImageScanReport findByProjectId(Long projectId) {
        return converter.toDomain(jpaRepository.findByProjectId(projectId));
    }
}
