package com.thoughtworks.ssr.business.imagescan.service;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanRequestEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanResultEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanStageEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository.ImageScanBusinessRepository;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository.ImageScanStageRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ImageScanBusinessService {

    private final ImageScanBusinessRepository imageScanBusinessRepository;
    private final ImageScanStageRepository imageScanStageRepository;
    private RestTemplate restTemplate = new RestTemplate();

    public String imageScanService(ImageScanRequestEntity request) {
        HttpStatus responseStatus = imageScanRequestService(request);
        if (responseStatus == HttpStatus.OK) {
            return "res";
        } else {
            return responseStatus.toString();
        }
    }

    public HttpStatus imageScanRequestService(ImageScanRequestEntity request) {
        ResponseEntity<HttpStatus> responseEntity = restTemplate.postForEntity("http://localhost:8090/start", request, HttpStatus.class);
        return responseEntity.getBody();
    }

    public ImageScanResultEntity getStepResult(String pj_id) {
        return imageScanBusinessRepository.findResultById(pj_id);
    }

    public ImageScanStageEntity getScanResult(String pj_id) {
        return imageScanStageRepository.findStageById(pj_id);
    }
}
