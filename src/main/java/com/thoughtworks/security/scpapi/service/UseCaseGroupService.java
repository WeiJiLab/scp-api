package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.controller.request.AddUseCaseRequest;
import com.thoughtworks.security.scpapi.controller.request.UseCaseGroupCreateRequest;
import com.thoughtworks.security.scpapi.entity.UseCaseGroupEntity;

import java.util.List;

public interface UseCaseGroupService {
    UseCaseGroupEntity create(UseCaseGroupCreateRequest useCaseGroupRequest);

    UseCaseGroupEntity findById(Integer id);

    List<UseCaseGroupEntity> findAll();

    void delete(Integer id);

    UseCaseGroupEntity update(Integer id, UseCaseGroupCreateRequest request);

    UseCaseGroupEntity addUseCase(Integer useGroupId, AddUseCaseRequest request);
}
