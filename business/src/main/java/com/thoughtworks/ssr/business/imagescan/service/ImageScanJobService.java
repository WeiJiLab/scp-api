package com.thoughtworks.ssr.business.imagescan.service;

import com.thoughtworks.ssr.domain.imagescan.exception.ImageScanException;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanCommand;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanReport;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanRequest;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanResult;
import com.thoughtworks.ssr.domain.imagescan.model.ImageScanStage;
import com.thoughtworks.ssr.domain.imagescan.model.StopCommand;
import com.thoughtworks.ssr.domain.imagescan.service.ImageScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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
        imageScanCommand.setProjectName(imageScanRequest.getProjectName());
        imageScanCommand.setTypeOption(imageScanRequest.getTypeOption());
        imageScanCommand.setCreateTime(getCreateTime());

//        ImageScanCommand savedCommand = imageScanService.saveJob(imageScanCommand);
//        HttpStatusCode responseStatus = imageScanRequestService(savedCommand);
//        if (responseStatus == OK) {
//            return savedCommand.getProjectId();
//        } else {
//            return imageScanCommand.getProjectId();
//        }
        return imageScanService.saveJob(imageScanCommand).getProjectId();
    }


    public List<ImageScanCommand> getAllImageScanJobs() throws ImageScanException {
        return imageScanService.getAllImageScanJobs();
    }

    public List<ImageScanResult> findResultsByProjectId(Long pjId) {
        return imageScanService.findAllResultsByProjectId(pjId);
    }

    public List<ImageScanStage> findStagesByProjectId(Long pjId) {
        return imageScanService.findAllStagesByProjectId(pjId);
    }

    public ImageScanStage saveStage(ImageScanStage imageScanStage) {
        imageScanStage.setTimeStamp(getCreateTime());
        return imageScanService.saveStage(imageScanStage);
    }

    public ImageScanResult saveResult(ImageScanResult imageScanResult) {
        imageScanResult.setTimeStamp(getCreateTime());
        return imageScanService.saveResult(imageScanResult);
    }

    public ImageScanReport saveScanReport(Long projectId, MultipartFile file) {
        ImageScanReport imageScanReport = new ImageScanReport();
        String url = upLoadReport(file, projectId);
        String reportName = file.getName();
        imageScanReport.setProjectId(projectId);
        imageScanReport.setFileName(reportName);
        imageScanReport.setScanReportUrl(url);
        imageScanReport.setCreateAt(getCreateTime());

        return imageScanService.saveReport(imageScanReport);
    }

    public String getScanReport(Long projectId) throws IOException {
        ImageScanReport imageScanReport = imageScanService.findByProjectId(projectId);
        String url = "file://" + imageScanReport.getScanReportUrl();
        URL downloadURL = new URL(url);
        URLConnection connection = downloadURL.openConnection();
        File file = getScanFile(connection);
        return "Download scan report to " + file.getAbsolutePath();
    }

    public String stopScan(StopCommand stopCommand) {
        HttpStatusCode statusCode = stopScanByProjectId(stopCommand);
//        if (statusCode == OK) {
//            return "Job " + stopCommand.getProjectId() + " has stopped";
//        } else {
//            return "Job " + stopCommand.getProjectId() + " stop failed...";
//        }
        return "stopped";
    }

    private HttpStatusCode stopScanByProjectId(StopCommand stopCommand) {
        ResponseEntity<HttpStatus> responseEntity = restTemplate.postForEntity("http://localhost:8090/stop", stopCommand, HttpStatus.class);
        return responseEntity.getStatusCode();
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

    public String upLoadReport(MultipartFile file, Long projectId) {
        String uploadPath = "/Users/zhengyuan.wang/projects/shangqi/scp-api/tmp";
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        File localFile = new File(uploadPath + File.separator + projectId + "_" + file.getOriginalFilename());
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return localFile.getAbsolutePath();
    }
    private static File getScanFile(URLConnection connection) throws IOException {
        InputStream is = connection.getInputStream();
        String downloadFilePath = System.getProperty("user.home");
        File file = new File(downloadFilePath + File.separator + "Desktop" + File.separator + "scan_report.docx");
        FileOutputStream os = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        os.close();
        return file;
    }

}
