package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.api.s3.OperateObject;
import com.thoughtworks.security.scpapi.entity.UseCaseEntity;
import com.thoughtworks.security.scpapi.exception.UseCaseNotFoundException;
import com.thoughtworks.security.scpapi.repository.ScanResultRepository;
import com.thoughtworks.security.scpapi.repository.UseCaseRepository;
import com.thoughtworks.security.scpapi.util.ZipUtil;
import lombok.AllArgsConstructor;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

import static com.thoughtworks.security.scpapi.util.ConstantsValue.*;


@AllArgsConstructor
public class ComplianceScanThread extends Thread {
    private Long resultId;
    private Long useCaseId;
    private ScanResultRepository securityResultRepository;
    private UseCaseRepository securityUseCaseRepository;

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

        return USE_CASE_PATH_TMP + "/" + keyName;
    }

    // 做一次抽象
    public void run() {
        try {
            UseCaseEntity useCaseItem = securityUseCaseRepository.findById(useCaseId)
                    .orElseThrow(UseCaseNotFoundException::new);
            String useCaseUrl = getUseCaseUrl(useCaseItem.getScriptPath());
            Date date = new Date();
            String currDate = new Timestamp(date.getTime()).toString().replaceAll("[[\\s-:punct:]]", "");
            String fileNamePre = useCaseItem.getName().replaceAll(" ", "");
            String reportName = fileNamePre + currDate + ".html";
            String reportFullPath = REPORT_PATH_TMP + "/" + reportName;
            // todo 改一下inspec exec
            String shellStr = "/bin/sh " + useCaseUrl + " " + REPORT_PATH_TMP + " " + reportFullPath;
            Process process = Runtime.getRuntime().exec(shellStr);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                System.out.println("==================call shell failed. error code is :" + exitValue);
                //securityResultRepository.updateState(resultId, ScanResultEnum.FAILED.getValue(), reportName);
                return;
            }

            //读出来再写
            OperateObject.putObject(reportFullPath, S3_BUCKET_NAME);
            //securityResultRepository.updateState(resultId, ScanResultEnum.SUCCESS.getValue(), reportName);
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
