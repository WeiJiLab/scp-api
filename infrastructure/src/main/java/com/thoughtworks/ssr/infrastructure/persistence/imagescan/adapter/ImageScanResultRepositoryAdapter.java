package com.thoughtworks.ssr.infrastructure.persistence.imagescan.adapter;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanResult;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanResultRepository;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.converter.ImageScanResultEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository.ImageScanResultJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public Optional<ImageScanResult> findAllByPjId(Long pjId) {
        return imageScanResultJpaRepository.findAllByPjId(pjId).map(converter::toDomain);
    }
}
