package com.thoughtworks.ssr.business.imagescan.service;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanRequest;
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

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public Long imageScanService(ImageScanRequest request) {
        ImageScanRequestEntity imageScanRequestEntity = new ImageScanRequestEntity();
        imageScanRequestEntity.setPj_id(System.currentTimeMillis()/1000L);
        imageScanRequestEntity.setPj_name(request.getPj_name());
        imageScanRequestEntity.setType_option(request.getType_option());
        imageScanRequestEntity.setCreate_time(getCreateTime());
        imageScanJobRepository.save(imageScanRequestEntity);
        return imageScanRequestEntity.getPj_id();
    }

    public HttpStatus imageScanRequestService(ImageScanRequestEntity request) {
        ResponseEntity<HttpStatus> responseEntity = restTemplate.postForEntity("http://localhost:8090/start", request, HttpStatus.class);
        return responseEntity.getBody();
    }

    public ImageScanStageEntity getStepResult(Long pj_id) {
        return imageScanBusinessRepository.findResultById(pj_id);
    }

    public ImageScanResultEntity getScanResult(Long pj_id) {
        return imageScanStageRepository.findStageById(pj_id);
    }

    public ResponseEntity<String> saveStageResult(ImageScanStageEntity resultEntity) {
        resultEntity.setTime_stamp(getCreateTime());
        imageScanBusinessRepository.save(resultEntity);
        return new ResponseEntity<>(OK);
    }

    public ResponseEntity<String> saveScanResult(ImageScanResultEntity scanStageEntity) {
        scanStageEntity.setTime_stamp(getCreateTime());
        imageScanStageRepository.save(scanStageEntity);
        return new ResponseEntity<>(OK);
    }

    private static String getCreateTime() {
        Long currentTimestamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(Long.parseLong(String.valueOf(currentTimestamp))));
    }
}
