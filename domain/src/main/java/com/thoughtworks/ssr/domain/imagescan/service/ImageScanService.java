package com.thoughtworks.ssr.domain.imagescan.service;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanCommand;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanResult;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanStage;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanJobRepository;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanResultRepository;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageScanService {

    private final ImageScanJobRepository imageScanJobRepository;

    private final ImageScanResultRepository imageScanResultRepository;

    private final ImageScanStageRepository imageScanStageRepository;

    public ImageScanCommand saveJob(ImageScanCommand imageScanCommand){
        return imageScanJobRepository.save(imageScanCommand);
    }

    public ImageScanResult saveResult(ImageScanResult imageScanResult) {
        return imageScanResultRepository.save(imageScanResult);
    }

    public ImageScanStage saveStage(ImageScanStage imageScanStage) {
        return imageScanStageRepository.save(imageScanStage);
    }

    public Optional<ImageScanStage> findAllStagesByPjId(Long pjId) {
        return imageScanStageRepository.findAllByPjId(pjId);
    }

    public Optional<ImageScanResult> findAllResultsByPjId(Long pjId) {
        return imageScanResultRepository.findAllByPjId(pjId);
    }
}
