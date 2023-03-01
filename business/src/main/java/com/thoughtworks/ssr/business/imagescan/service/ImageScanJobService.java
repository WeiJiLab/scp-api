package com.thoughtworks.ssr.business.imagescan.service;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanCommand;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanRequest;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanResult;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanStage;
import com.thoughtworks.ssr.domain.imagescan.service.ImageScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageScanJobService {

    private final ImageScanService imageScanService;
    private final RestTemplate restTemplate = new RestTemplate();

    public Long saveImageScanJob(ImageScanRequest imageScanRequest) {
        ImageScanCommand imageScanCommand = new ImageScanCommand();
        imageScanCommand.setPjName(imageScanRequest.getPjName());
        imageScanCommand.setTypeOption(imageScanRequest.getTypeOption());
        imageScanCommand.setCreateTime(getCreateTime());

        imageScanService.saveJob(imageScanCommand);

//        HttpStatusCode responseStatus = imageScanRequestService(imageScanCommand);
//        if (responseStatus == OK) {
//            imageScanService.saveJob(imageScanCommand);
//        }

        return imageScanCommand.getPjId();
    }


    public List<ImageScanCommand> getAllImageScanJobs() {
        return imageScanService.getAllImageScanJobs();
    }

    public List<ImageScanResult> findResultsByPjId(Long pjId) {
        return imageScanService.findAllResultsByPjId(pjId);
    }

    public List<ImageScanStage> findStagesByPjId(Long pjId) {
        return imageScanService.findAllStagesByPjId(pjId);
    }

    public ImageScanStage saveStage(ImageScanStage imageScanStage) {
        imageScanStage.setTimeStamp(getCreateTime());
        return imageScanService.saveStage(imageScanStage);
    }

    public ImageScanResult saveResult(ImageScanResult imageScanResult) {
        imageScanResult.setTimeStamp(getCreateTime());
        return imageScanService.saveResult(imageScanResult);
    }

    public HttpStatusCode imageScanRequestService(ImageScanCommand request) {
        ResponseEntity<HttpStatus> responseEntity = restTemplate.postForEntity("http://localhost:8090/start", request, HttpStatus.class);
        return responseEntity.getStatusCode();
    }

    private static String getCreateTime() {
        Long currentTimestamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(Long.parseLong(String.valueOf(currentTimestamp))));
    }

}
