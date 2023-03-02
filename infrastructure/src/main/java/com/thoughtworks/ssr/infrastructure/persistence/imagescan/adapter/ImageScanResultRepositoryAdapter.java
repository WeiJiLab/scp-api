package com.thoughtworks.ssr.infrastructure.persistence.imagescan.adapter;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanResult;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanResultRepository;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.converter.ImageScanResultEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanResultEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository.ImageScanResultJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageScanResultRepositoryAdapter implements ImageScanResultRepository {

    private final ImageScanResultEntityConverter converter;

    private final ImageScanResultJpaRepository imageScanResultJpaRepository;

    @Override
    public ImageScanResult save(ImageScanResult imageScanResult) {
        var imageScanResultEntity = imageScanResultJpaRepository.save(converter.from(imageScanResult));
        return converter.toDomain(imageScanResultEntity);
    }

    @Override
    public List<ImageScanResult> findAllByProjectId(Long pjId) {
        List<ImageScanResultEntity> resultEntities = imageScanResultJpaRepository.findAllByProjectId(pjId);
        List<ImageScanResult> allResults = new ArrayList<>();
        for (ImageScanResultEntity result : resultEntities) {
            ImageScanResult imageScanResult = converter.toDomain(result);
            allResults.add(imageScanResult);
        }
        return allResults;
    }
}
