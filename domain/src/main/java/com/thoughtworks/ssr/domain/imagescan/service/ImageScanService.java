package com.thoughtworks.ssr.domain.imagescan.service;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanCommand;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanResult;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanStage;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanJobRepository;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanResultRepository;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageScanService {

    private final ImageScanJobRepository imageScanJobRepository;

    private final ImageScanResultRepository imageScanResultRepository;

    private final ImageScanStageRepository imageScanStageRepository;

//    private final ImageScanReportRepository imageScanReportRepository;

    public ImageScanCommand saveJob(ImageScanCommand imageScanCommand){
        return imageScanJobRepository.save(imageScanCommand);
    }

    public ImageScanResult saveResult(ImageScanResult imageScanResult) {
        return imageScanResultRepository.save(imageScanResult);
    }

    public ImageScanStage saveStage(ImageScanStage imageScanStage) {
        return imageScanStageRepository.save(imageScanStage);
    }

    public List<ImageScanStage> findAllStagesByProjectId(Long pjId) {
        return imageScanStageRepository.findAllByProjectId(pjId);
    }

    public List<ImageScanResult> findAllResultsByProjectId(Long pjId) {
        return imageScanResultRepository.findAllByProjectId(pjId);
    }

    public List<ImageScanCommand> getAllImageScanJobs() {
        return imageScanJobRepository.findAll();
    }

//    public ImageScanReport saveReport(ImageScanReport imageScanReport) {
//        return imageScanReportRepository.save(imageScanReport);
//    }
}
