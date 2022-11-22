package com.thoughtworks.ssr.domain.service;

import com.thoughtworks.ssr.common.utils.TimeUtils;
import com.thoughtworks.ssr.common.utils.ZipUtil;
import com.thoughtworks.ssr.domain.scan.enums.ScanResultEnum;
import com.thoughtworks.ssr.domain.scan.enums.ScanTaskEnum;
import com.thoughtworks.ssr.domain.scan.model.ScanResult;
import com.thoughtworks.ssr.domain.scan.model.ScanTask;
import com.thoughtworks.ssr.domain.scan.service.ScanResultService;
import com.thoughtworks.ssr.domain.scan.service.ScanTaskService;
import com.thoughtworks.ssr.domain.usecase.service.UseCaseService;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class ComplianceScanThread extends Thread {

    public static final String REPORT_PATH_TMP = "/tmp/compliance_report";
    public static final String USE_CASE_PATH_TMP = "/tmp/usecase";
    public static final String S3_BUCKET_NAME = "sss-inspec-report-bucket-ap-northeast-1";

    private final ScanTask scanTask;
    private final ScanResultService scanResultService;
    private final ScanTaskService scanTaskService;
    private final UseCaseService useCaseService;
    private final EnvironmentTypePara environmentTypePara;

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
//        String zipSrcName = OperateObject.downloadObject(S3_BUCKET_NAME, keyName, USE_CASE_PATH_TMP);
        String zipSrcName = "";
        String lastName = keyName.substring(0, keyName.lastIndexOf("."));
        String objDir = USE_CASE_PATH_TMP + "/" + lastName;
        ZipUtil.unZip(zipSrcName, objDir);
        File fileObj = new File(objDir);
        File[] files = fileObj.listFiles();
        assert files != null;
        List<File> validFiles = Arrays.stream(files).filter(f -> !f.isHidden()).toList();
        if (validFiles.size() != 1) { // 要求上传zip包中只包含一个目录
            throw new RuntimeException("Use case is invalid.");
        }
        return objDir + "/" + validFiles.get(0).getName();
    }

    public EnvironmentTypePara getEnvironmentPara() {
        return environmentTypePara;
    }

    public String getScanEnvironmentCmd() {
        return "";
    }

    // 做一次抽象
    public void run() {
        try {

            // useCase
            var useCase = useCaseService.findById(scanTask.getUseCaseId());

            // task
            scanTask.setStatus(ScanTaskEnum.RUNNING);
            scanTask.setStartTime(TimeUtils.getOffsetDateTimeNow());
            scanTaskService.create(scanTask);

            // result
            var scanResult = scanResultService.create(
                    ScanResult.builder()
                            .result(ScanResultEnum.SCANNING)
                            .resultPath("")
                            .scanTaskId(scanTask.getId())
                            .useCaseId(useCase.getId())
                            .build()
            );


            String useCaseUrl = getUseCaseUrl(useCase.getScriptPath());

            Date date = new Date();
            String currDate = new Timestamp(date.getTime()).toString().replaceAll("[\\s-:punct:]", "");
            String fileNamePre = useCase.getName().replaceAll(" ", "");
            String reportName = fileNamePre + currDate + ".html";
            String reportFullPath = REPORT_PATH_TMP + "/" + reportName;
            String environmentCmdStr = getScanEnvironmentCmd();
            String shellStr = "inspec exec " + useCaseUrl + environmentCmdStr + " --reporter html:" + reportFullPath;
            Process process = Runtime.getRuntime().exec(shellStr);
            int exitValue = process.waitFor();
            printStream(process);
            scanTask.setEndTime(TimeUtils.getOffsetDateTimeNow());
            if ((exitValue == 100) || (exitValue == 101) || (exitValue == 0)) {
                // success
                scanTask.setStatus(ScanTaskEnum.DONE);

                scanTaskService.update(scanTask);

                scanResult.setResultPath(reportName);
                scanResult.setResult(ScanResultEnum.SUCCESS);
                scanResultService.update(scanResult);

                //读出来再写
//                OperateObject.putObject(reportFullPath, S3_BUCKET_NAME);
                //securityResultRepository.updateState(resultId, ScanResultEnum.SUCCESS.getValue(), reportName);
                return;
            }

            scanTask.setStatus(ScanTaskEnum.FAILED);
            scanTaskService.update(scanTask);

            scanResult.setResult(ScanResultEnum.FAILED);
            scanResultService.update(scanResult);

            System.out.println("==================call shell failed. error code is :" + exitValue);
            //securityResultRepository.updateState(resultId, ScanResultEnum.FAILED.getValue(), reportName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
