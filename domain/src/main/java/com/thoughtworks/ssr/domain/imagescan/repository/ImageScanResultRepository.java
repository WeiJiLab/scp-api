package com.thoughtworks.ssr.domain.imagescan.repository;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanResult;

import java.util.List;

public interface ImageScanResultRepository {

    ImageScanResult save(ImageScanResult imageScanResult);

    List<ImageScanResult> findAllByProjectId(Long id);
}
