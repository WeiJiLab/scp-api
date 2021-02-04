package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.api.s3.OperateObject;
import com.thoughtworks.security.scpapi.domain.ScanResult;
import com.thoughtworks.security.scpapi.entity.ScanResultEntity;
import com.thoughtworks.security.scpapi.entity.ScanTaskEntity;
import com.thoughtworks.security.scpapi.entity.UseCaseEntity;
import com.thoughtworks.security.scpapi.enums.ScanTaskEnum;
import com.thoughtworks.security.scpapi.exception.UseCaseNotFoundException;
import com.thoughtworks.security.scpapi.repository.ScanResultRepository;
import com.thoughtworks.security.scpapi.repository.ScanTaskRepository;
import com.thoughtworks.security.scpapi.repository.UseCaseRepository;
import com.thoughtworks.security.scpapi.util.ScanResultEnum;
import com.thoughtworks.security.scpapi.util.ZipUtil;
import lombok.AllArgsConstructor;

import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import static com.thoughtworks.security.scpapi.util.ConstantsValue.*;


@AllArgsConstructor
public class ComplianceScanThread extends Thread {
    private final ScanTaskEntity scanTaskEntity;
    private final ScanResultRepository scanResultRepository;
    private final ScanTaskRepository scanTaskRepository;
    private final UseCaseRepository useCaseRepository;

    private void printStream(Process process) {
        // 以下为调试代码
        InputStream fis = process.getInputStream();
        // 用一个读输出流类去读
        InputStreamReader isr = new InputStreamReader(fis);
        // 用缓冲器读行
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        // 直到读完为止
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUseCaseUrl(String keyName) throws IOException {

        String zipSrcName = OperateObject.downloadObject(S3_BUCKET_NAME, keyName, USE_CASE_PATH_TMP);
        String lastName = keyName.substring(0, keyName.lastIndexOf("."));
        ZipUtil.unZip(zipSrcName, USE_CASE_PATH_TMP + "/" + lastName);

        return USE_CASE_PATH_TMP + "/" + lastName;
    }

    // 做一次抽象
    public void run() {
        try {

            // useCase
            UseCaseEntity useCaseEntity = useCaseRepository.findById(scanTaskEntity.getUseCaseId())
                    .orElseThrow(UseCaseNotFoundException::new);

            // task
            scanTaskEntity.setStatus(ScanTaskEnum.RUNNING);
            scanTaskEntity.setStartTime(Instant.now());
            scanTaskRepository.saveAndFlush(scanTaskEntity);

            // result
            ScanResultEntity scanResultEntity = scanResultRepository.saveAndFlush(
                    ScanResultEntity.builder()
                            .result(0)
                            .resultPath("")
                            .scanTaskId(scanTaskEntity.getId())
                            .useCaseId(useCaseEntity.getId())
                            .build());


            String useCaseUrl = getUseCaseUrl(useCaseEntity.getScriptPath());

            Date date = new Date();
            String currDate = new Timestamp(date.getTime()).toString().replaceAll("[[\\s-:punct:]]", "");
            String fileNamePre = useCaseEntity.getName().replaceAll(" ", "");
            String reportName = fileNamePre + currDate + ".html";
            String reportFullPath = REPORT_PATH_TMP + "/" + reportName;
            // todo 改一下inspec exec
            String shellStr = "inspec exec " + useCaseUrl + "/my_puppet --reporter html:"  + reportFullPath;
            Process process = Runtime.getRuntime().exec(shellStr);
            int exitValue = process.waitFor();
            scanTaskEntity.setEndTime(Instant.now());
            if ((exitValue == 100) || (exitValue == 101) || (exitValue == 0)) {
                // success
                scanTaskEntity.setStatus(ScanTaskEnum.DONE);

                scanTaskRepository.saveAndFlush(scanTaskEntity);

                scanResultEntity.setResultPath(reportName);
                scanResultEntity.setResult(2);
                scanResultRepository.saveAndFlush(scanResultEntity);

                //读出来再写
                OperateObject.putObject(reportFullPath, S3_BUCKET_NAME);
                //securityResultRepository.updateState(resultId, ScanResultEnum.SUCCESS.getValue(), reportName);
                return;
            }

            // error
            scanTaskEntity.setStatus(ScanTaskEnum.FAILED);
            scanTaskRepository.saveAndFlush(scanTaskEntity);
            
            scanResultEntity.setResult(1);
            scanResultRepository.saveAndFlush(scanResultEntity);

            System.out.println("==================call shell failed. error code is :" + exitValue);
            //securityResultRepository.updateState(resultId, ScanResultEnum.FAILED.getValue(), reportName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                // nothing
            }
        }
    }
}
