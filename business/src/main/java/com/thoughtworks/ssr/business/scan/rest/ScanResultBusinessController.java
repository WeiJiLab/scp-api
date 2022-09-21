package com.thoughtworks.ssr.business.scan.rest;

import com.thoughtworks.ssr.business.scan.service.ScanResultBusinessService;
import com.thoughtworks.ssr.business.scan.usecases.GetScanResultCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scanResults")
@RequiredArgsConstructor
public class ScanResultBusinessController {

    private final ScanResultBusinessService scanResultBusinessService;

    @GetMapping("/{id}")
    public GetScanResultCase.Response findById(@PathVariable("id") Long id) {
        var scanResult = scanResultBusinessService.findById(id);
        return GetScanResultCase.Response.from(scanResult);
    }
}
