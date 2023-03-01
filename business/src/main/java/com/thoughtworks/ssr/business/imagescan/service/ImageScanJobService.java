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
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

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

    public Optional<ImageScanResult> findResultsByPjId(Long pjId) {
        return imageScanService.findAllResultsByPjId(pjId);
    }

    public Optional<ImageScanStage> findStagesByPjId(Long pjId) {
        return imageScanService.findAllStagesByPjId(pjId);
    }

    public ResponseEntity<String> saveStage(ImageScanStage imageScanStage) {
        imageScanStage.setTimeStamp(getCreateTime());
        imageScanService.saveStage(imageScanStage);
        return new ResponseEntity<>(OK);
    }

    public ResponseEntity<String> saveResult(ImageScanResult imageScanResult) {
        imageScanResult.setTimeStamp(getCreateTime());
        imageScanService.saveResult(imageScanResult);
        return new ResponseEntity<>(OK);
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
