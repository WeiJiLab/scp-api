package com.thoughtworks.ssr.infrastructure.persistence.imagescan.adapter;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanStage;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanStageRepository;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.converter.ImageScanStageEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanStageEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository.ImageScanStageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageScanStageRepositoryAdapter implements ImageScanStageRepository {

    private final ImageScanStageEntityConverter converter;

    private final ImageScanStageJpaRepository jpaRepository;

    @Override
    public ImageScanStage save(ImageScanStage imageScanStage) {
        var imageScanStageEntity = jpaRepository.save(converter.from(imageScanStage));
        return converter.toDomain(imageScanStageEntity);
    }

    @Override
    public List<ImageScanStage> findAllByPjId(Long pjId) {
        List<ImageScanStageEntity> stageEntities = jpaRepository.findAllByPjId(pjId);
        List<ImageScanStage> allStages = new ArrayList<>();
        for (ImageScanStageEntity stage : stageEntities) {
            ImageScanStage imageScanStage = converter.toDomain(stage);
            allStages.add(imageScanStage);
        }
        return allStages;
    }
}
