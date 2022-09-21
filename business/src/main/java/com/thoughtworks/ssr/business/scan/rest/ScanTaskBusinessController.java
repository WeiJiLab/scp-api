package com.thoughtworks.ssr.business.scan.rest;

import com.thoughtworks.ssr.business.scan.service.ScanTaskBusinessService;
import com.thoughtworks.ssr.business.scan.usecases.GetScanTaskCase;
import com.thoughtworks.ssr.domain.scan.model.ScanTask;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/scanTasks")
@RequiredArgsConstructor
public class ScanTaskBusinessController {

    private final ScanTaskBusinessService scanTaskBusinessService;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public GetScanTaskCase.Response findById(@PathVariable("id") Long id) {
        ScanTask scanTask = scanTaskBusinessService.findById(id);
        return GetScanTaskCase.Response.from(scanTask);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        scanTaskBusinessService.deleteById(id);
    }

}
