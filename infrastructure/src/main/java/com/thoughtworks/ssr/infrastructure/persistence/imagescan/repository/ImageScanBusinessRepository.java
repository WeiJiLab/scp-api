package com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanResultEntity;

public interface ImageScanBusinessRepository {
    ImageScanResultEntity findStepsById(String pj_id);
}
