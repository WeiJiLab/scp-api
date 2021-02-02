package com.thoughtworks.security.scpapi.controller;


import com.thoughtworks.security.scpapi.entity.ScanResultEntity;

import com.thoughtworks.security.scpapi.service.ScanResultService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scanResults")
@RequiredArgsConstructor
public class ScanResultController {

    private final ScanResultService scanResultService;

    @ApiOperation(value = "查询扫描任务结果")
    @GetMapping("{appId}")
    public List<ScanResultEntity> findByAppId(@PathVariable Integer appId) {
        return scanResultService.findByAppId(appId);
    }

}
