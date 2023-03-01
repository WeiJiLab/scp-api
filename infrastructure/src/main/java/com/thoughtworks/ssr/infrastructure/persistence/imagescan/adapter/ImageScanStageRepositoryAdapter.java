package com.thoughtworks.ssr.infrastructure.persistence.imagescan.adapter;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanStage;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanStageRepository;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.converter.ImageScanStageEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository.ImageScanStageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public Optional<ImageScanStage> findAllByPjId(Long pjId) {
        return jpaRepository.findAllByPjId(pjId).map(converter::toDomain);
    }
}
