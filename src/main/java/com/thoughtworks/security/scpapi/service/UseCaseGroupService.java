package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.controller.request.AddUseCaseRequest;
import com.thoughtworks.security.scpapi.controller.request.UseCaseGroupCreateRequest;
import com.thoughtworks.security.scpapi.entity.UseCaseGroupEntity;

import java.util.List;

public interface UseCaseGroupService {
    UseCaseGroupEntity create(UseCaseGroupCreateRequest useCaseGroupRequest);

    UseCaseGroupEntity findById(Long id);

    List<UseCaseGroupEntity> findAll();

    void delete(Long id);

    UseCaseGroupEntity update(Long id, UseCaseGroupCreateRequest request);

    UseCaseGroupEntity addUseCase(Long useGroupId, AddUseCaseRequest request);
}
