package com.thoughtworks.ssr.business.imagescan.rest;

import com.thoughtworks.ssr.business.imagescan.service.ImageScanBusinessService;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanRequestEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanResultEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanStageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/image-scan")
@RequiredArgsConstructor
public class ImageScanBusinessController {
    @Autowired
    private final ImageScanBusinessService imageScanBusinessService;

    @PostMapping("/start")
    @ResponseStatus(OK)
    public Long imageScan(@RequestBody ImageScanRequestEntity request) {
        Long currentTimestamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(currentTimestamp))));
        request.setCreate_time(sd);
        return imageScanBusinessService.imageScanService(request);
    }

    @GetMapping("/stage-status/{pj_id}")
    @ResponseStatus(OK)
    public ImageScanStageEntity getStepResult(@PathVariable(value = "pj_id") Long pj_id) {
        return imageScanBusinessService.getStepResult(pj_id);
    }

    @PostMapping(value = "/stage-status")
    @ResponseBody
    public ResponseEntity<String> saveStageResult(@RequestBody ImageScanStageEntity resultEntity) {
        return imageScanBusinessService.saveStageResult(resultEntity);
    }

    @GetMapping("/steps/{pj_id}")
    @ResponseStatus(OK)
    public ImageScanResultEntity getScanResult(@PathVariable(value = "pj_id") Long pj_id) {
        return imageScanBusinessService.getScanResult(pj_id);
    }

    @PostMapping(value = "/detail-result")
    public ResponseEntity<String> saveScanResult(@RequestBody ImageScanResultEntity scanStageEntity) {
        return imageScanBusinessService.saveScanResult(scanStageEntity);
    }
}
