package com.thoughtworks.ssr.business.imagescan.rest;

import com.thoughtworks.ssr.business.imagescan.service.ImageScanBusinessService;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanRequestEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanResultEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanStageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/no-source-scan")
@RequiredArgsConstructor
public class ImageScanBusinessController {
    private final ImageScanBusinessService imageScanBusinessService;

    @PostMapping
    @ResponseStatus(OK)
    public String imageScan(@RequestBody ImageScanRequestEntity request) {
        return imageScanBusinessService.imageScanService(request);
    }

    @GetMapping("/result")
    @ResponseStatus(OK)
    public ImageScanResultEntity getStepResult(@RequestBody String pj_id) {
        return imageScanBusinessService.getStepResult(pj_id);
    }
    @GetMapping("/steps")
    @ResponseStatus(OK)
    public ImageScanStageEntity getScanResult(@RequestBody String pj_id) {
        return imageScanBusinessService.getScanResult(pj_id);
    }
}
