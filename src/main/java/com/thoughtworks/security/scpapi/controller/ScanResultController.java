package com.thoughtworks.security.scpapi.controller;


import com.thoughtworks.security.scpapi.domain.ScanResult;
import com.thoughtworks.security.scpapi.entity.ScanResultEntity;
import com.thoughtworks.security.scpapi.service.ScanResultService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scanResults")
@RequiredArgsConstructor
public class ScanResultController {

    private final ScanResultService scanResultService;

    @ApiOperation(value = "通过 ResultId 查询扫描任务结果")
    @GetMapping("/{resultId}")
    public ScanResult findById(@PathVariable Integer resultId) {
        return scanResultService.findById(resultId);
    }

    @ApiOperation(value = "通过 AppId 查询扫描任务结果")
    @GetMapping("/appId/{appId}")
    public List<ScanResult> findByAppId(@PathVariable Integer appId) {
        return scanResultService.findByAppId(appId);
    }

    @ApiOperation(value = "通过 taskId 查询扫描任务结果")
    @GetMapping("/taskId/{taskId}")
    public ScanResult findByTaskId(@PathVariable Integer taskId) {
        return scanResultService.findByTaskId(taskId);
    }

}
