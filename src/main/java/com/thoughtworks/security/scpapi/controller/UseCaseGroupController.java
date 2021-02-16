package com.thoughtworks.security.scpapi.controller;

import com.thoughtworks.security.scpapi.controller.request.AddUseCaseRequest;
import com.thoughtworks.security.scpapi.controller.request.UseCaseGroupCreateRequest;
import com.thoughtworks.security.scpapi.entity.UseCaseGroupEntity;
import com.thoughtworks.security.scpapi.service.UseCaseGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/useCaseGroups")
@RequiredArgsConstructor
public class UseCaseGroupController {

    private final UseCaseGroupService useCaseGroupService;

    @PostMapping
    @ResponseStatus(CREATED)
    public UseCaseGroupEntity create(@Validated @RequestBody UseCaseGroupCreateRequest useCaseGroupRequest) {
        return useCaseGroupService.create(useCaseGroupRequest);
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public UseCaseGroupEntity findById(@PathVariable Long id) {
        return useCaseGroupService.findById(id);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<UseCaseGroupEntity> findAll() {
        return useCaseGroupService.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable Long id) {
        useCaseGroupService.delete(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(OK)
    public UseCaseGroupEntity update(@PathVariable Long id,
                                     @Validated @RequestBody UseCaseGroupCreateRequest useCaseGroupCreateRequest) {
        return useCaseGroupService.update(id, useCaseGroupCreateRequest);
    }


    @PostMapping("/{useGroupId}/useCases")
    @ResponseStatus(CREATED)
    public UseCaseGroupEntity addUseCase(@PathVariable Long useGroupId,
                                         @RequestBody AddUseCaseRequest request) {
        return useCaseGroupService.addUseCase(useGroupId, request);
    }
}
