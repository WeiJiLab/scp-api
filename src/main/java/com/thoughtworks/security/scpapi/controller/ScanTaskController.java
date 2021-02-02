package com.thoughtworks.security.scpapi.controller;

import com.thoughtworks.security.scpapi.controller.request.ScanTaskRequest;
import com.thoughtworks.security.scpapi.controller.request.TaskSearchRequest;
import com.thoughtworks.security.scpapi.entity.ScanTaskEntity;
import com.thoughtworks.security.scpapi.service.ScanTaskService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/scanTasks")
@RequiredArgsConstructor
public class ScanTaskController {

    private final ScanTaskService scanTaskService;

    @ApiOperation(value = "启动合规检测")
    @PostMapping
    public List<ScanTaskEntity> startSecurityCheck(@Valid @RequestBody ScanTaskRequest startComplianceDto) {
        return scanTaskService.create(startComplianceDto);
    }

    @ApiOperation(value = "查询扫描任务列表")
    @GetMapping
    public List<ScanTaskEntity> search(TaskSearchRequest request) {
        return scanTaskService.search(request);
    }

    @ApiOperation(value = "查询扫描任务列表")
    @GetMapping("/{id}")
    public ScanTaskEntity findById(@PathVariable Integer id) {
        return scanTaskService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable Long id) {
        scanTaskService.delete(id);
    }

}
