package com.thoughtworks.ssr.domain.imagescan.repository;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanStage;

import java.util.List;

public interface ImageScanStageRepository {

    ImageScanStage save(ImageScanStage imageScanStage);

    List<ImageScanStage> findAllByProjectId(Long pjId);

}
