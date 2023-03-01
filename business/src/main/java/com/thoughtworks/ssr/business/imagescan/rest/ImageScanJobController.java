package com.thoughtworks.ssr.business.imagescan.rest;

import com.thoughtworks.ssr.business.imagescan.service.ImageScanJobService;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanCommand;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanRequest;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanResult;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanStage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/image-scan")
@RequiredArgsConstructor
public class ImageScanJobController {
    private final ImageScanJobService imageScanJobService;

    @PostMapping("/start")
    @ResponseStatus(OK)
    public Long imageScan(@RequestBody ImageScanRequest request) {
        return imageScanJobService.saveImageScanJob(request);
    }

    @GetMapping("/jobs")
    @ResponseStatus(OK)
    public List<ImageScanCommand> getAllImageScanCommand() {
        return imageScanJobService.getAllImageScanJobs();
    }

    @GetMapping("/stage-status/{pj_id}")
    @ResponseStatus(OK)
    public Optional<ImageScanStage> getStepResult(@PathVariable(value = "pj_id") Long pjId) {
        return imageScanJobService.findStagesByPjId(pjId);
    }

    @PostMapping(value = "/stage-status")
    @ResponseBody
    public ResponseEntity<String> saveStageResult(@RequestBody ImageScanStage resultEntity) {
        return imageScanJobService.saveStage(resultEntity);
    }

    @GetMapping("/steps/{pj_id}")
    @ResponseStatus(OK)
    public Optional<ImageScanResult> getScanResult(@PathVariable(value = "pj_id") Long pjId) {
        return imageScanJobService.findResultsByPjId(pjId);
    }

    @PostMapping(value = "/detail-result")
    public ResponseEntity<String> saveScanResult(@RequestBody ImageScanResult scanStageEntity) {
        return imageScanJobService.saveResult(scanStageEntity);
    }
}
