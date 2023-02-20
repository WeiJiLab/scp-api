package com.thoughtworks.ssr.infrastructure.persistence.imagescan.adapter;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanResultEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository.ImageScanBusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageScanBusinessAdapter implements ImageScanBusinessRepository {
    @Override
    public ImageScanResultEntity findStepsById(String pj_id) {
        return null;
    }
}
