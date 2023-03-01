package com.thoughtworks.ssr.domain.imagescan.repository;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanStage;

import java.util.Optional;

public interface ImageScanStageRepository {

    ImageScanStage save(ImageScanStage imageScanStage);

    Optional<ImageScanStage> findAllByPjId(Long pjId);

}
