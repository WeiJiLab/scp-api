package com.thoughtworks.ssr.business.imagescan.service;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanRequestEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanResultEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanStageEntity;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository.ImageScanBusinessRepository;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository.ImageScanJobRepository;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository.ImageScanStageRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ImageScanBusinessService {
    @Autowired
    private final ImageScanBusinessRepository imageScanBusinessRepository;
    @Autowired
    private final ImageScanStageRepository imageScanStageRepository;
    @Autowired
    private final ImageScanJobRepository imageScanJobRepository;
    private RestTemplate restTemplate = new RestTemplate();

    public Long imageScanService(ImageScanRequestEntity request) {
        HttpStatus responseStatus = imageScanRequestService(request);
        if (responseStatus == OK) {
            //save
            imageScanJobRepository.save(request);
        }
        return request.getPj_id();
    }

    public HttpStatus imageScanRequestService(ImageScanRequestEntity request) {
        ResponseEntity<HttpStatus> responseEntity = restTemplate.postForEntity("http://localhost:8090/start", request, HttpStatus.class);
        return responseEntity.getBody();
    }

    public ImageScanResultEntity getStepResult(Long pj_id) {
        return imageScanBusinessRepository.findResultById(pj_id);
    }

    public ImageScanStageEntity getScanResult(Long pj_id) {
        return imageScanStageRepository.findStageById(pj_id);
    }

    public ResponseEntity<String> saveStageResult(ImageScanResultEntity resultEntity) {
        imageScanBusinessRepository.save(resultEntity);
        return new ResponseEntity<>(OK);
    }

    public ResponseEntity<String> saveScanResult(ImageScanStageEntity scanStageEntity) {
        imageScanStageRepository.save(scanStageEntity);
        return new ResponseEntity<>(OK);
    }
}
