package com.thoughtworks.ssr.domain.imagescan.repository;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanResult;

import java.util.Optional;

public interface ImageScanResultRepository {

    ImageScanResult save(ImageScanResult imageScanResult);

    Optional<ImageScanResult> findAllByPjId(Long id);
}
