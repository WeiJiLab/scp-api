package com.thoughtworks.security.scpapi.controller;

import com.thoughtworks.security.scpapi.entity.UseCaseEntity;
import com.thoughtworks.security.scpapi.service.UseCaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Api(tags = {"合规用例管理的API接口"})
@RestController
@RequestMapping("/useCases")
@RequiredArgsConstructor
//TODO 用例写成一个通用的名字
public class UseCaseController {
    private final UseCaseService useCaseService;

    @ApiOperation(value = "上传合规用例")
    @PostMapping
    @ResponseStatus(CREATED)
    public UseCaseEntity uploadComplianceUseCase(@RequestParam("file") MultipartFile file,
                                                 @RequestParam String description,
                                                 @RequestParam String name,
                                                 @RequestParam Integer toolId) {
        return useCaseService.addUseCase(file, description, name, toolId);
    }

    @ApiOperation(value = "删除合规用例")
    @DeleteMapping({"{id}"})
    @ResponseStatus(OK)
    public void deleteComplianceUseCase(@PathVariable Long id) {
        useCaseService.deleteById(id);
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public UseCaseEntity findById(@PathVariable Long id) {
        return useCaseService.findById(id);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<UseCaseEntity> findAll() {
        return useCaseService.findAll();
    }


    @PatchMapping("{id}")
    @ResponseStatus(OK)
    public UseCaseEntity update(@PathVariable Long id,
                                @RequestParam("file") MultipartFile file,
                                @RequestParam String description,
                                @RequestParam String name,
                                @RequestParam Integer toolId) {
        return useCaseService.update(id, file, description, name, toolId);
    }


}
