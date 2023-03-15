package com.thoughtworks.ssr.domain.imagescan.service;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanCommand;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanReport;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanResult;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanStage;
import com.thoughtworks.ssr.domain.imagescan.model.UnfixImage;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanJobRepository;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanReportRepository;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanResultRepository;
import com.thoughtworks.ssr.domain.imagescan.repository.ImageScanStageRepository;
import com.thoughtworks.ssr.domain.imagescan.repository.UnfixImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageScanService {

    private final ImageScanJobRepository imageScanJobRepository;

    private final ImageScanResultRepository imageScanResultRepository;

    private final ImageScanStageRepository imageScanStageRepository;

    private final ImageScanReportRepository imageScanReportRepository;

    private final UnfixImageRepository unfixImageRepository;

    public ImageScanCommand saveJob(ImageScanCommand imageScanCommand){
        return imageScanJobRepository.save(imageScanCommand);
    }

    public ImageScanResult saveResult(ImageScanResult imageScanResult) {
        return imageScanResultRepository.save(imageScanResult);
    }

    public ImageScanStage saveStage(ImageScanStage imageScanStage) {
        return imageScanStageRepository.save(imageScanStage);
    }

    public List<ImageScanStage> findAllStagesByProjectId(Long pjId)  {
        return imageScanStageRepository.findAllByProjectId(pjId);
    }

    public List<ImageScanResult> findAllResultsByProjectId(Long pjId) {
        return imageScanResultRepository.findAllByProjectId(pjId);
    }

    public List<ImageScanCommand> getAllImageScanJobs() {
        return imageScanJobRepository.findAll();
    }

    public ImageScanReport saveReport(ImageScanReport imageScanReport) {
        return imageScanReportRepository.save(imageScanReport);
    }

    public ImageScanReport findByProjectId(Long projectId) {
        return imageScanReportRepository.findByProjectId(projectId);
    }

    public UnfixImage saveUnfixImage(UnfixImage unfixImage) {
        return unfixImageRepository.save(unfixImage);
    }
}
