package com.thoughtworks.security.scpapi.service.impl;

import com.thoughtworks.security.scpapi.controller.request.AddUseCaseRequest;
import com.thoughtworks.security.scpapi.controller.request.UseCaseGroupCreateRequest;
import com.thoughtworks.security.scpapi.entity.UseCaseGroupEntity;
import com.thoughtworks.security.scpapi.exception.core.DuplicatedException;
import com.thoughtworks.security.scpapi.exception.core.NotFoundException;
import com.thoughtworks.security.scpapi.repository.UseCaseGroupRepository;
import com.thoughtworks.security.scpapi.repository.UseCaseRepository;
import com.thoughtworks.security.scpapi.service.UseCaseGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.thoughtworks.security.scpapi.exception.core.DuplicatedError.EXISTED_USE_CASE_GROUP;
import static com.thoughtworks.security.scpapi.exception.core.NotFoundError.NOT_FOUND_USE_CASE_GROUP;

@Service
@RequiredArgsConstructor
public class UseCaseGroupServiceImpl implements UseCaseGroupService {

    private final UseCaseGroupRepository useCaseGroupRepository;
    private final UseCaseRepository useCaseRepository;

    @Override
    public UseCaseGroupEntity create(UseCaseGroupCreateRequest request) {

        checkUseCaseByName(request);

        UseCaseGroupEntity useCaseGroupEntity = UseCaseGroupEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return useCaseGroupRepository.save(useCaseGroupEntity);
    }

    @Override
    public UseCaseGroupEntity findById(Long id) {
        return useCaseGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USE_CASE_GROUP));
    }

    @Override
    public List<UseCaseGroupEntity> findAll() {
        return useCaseGroupRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        try {
            useCaseGroupRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new NotFoundException(NOT_FOUND_USE_CASE_GROUP);
        }
    }

    @Override
    public UseCaseGroupEntity update(Long id, UseCaseGroupCreateRequest request) {

        UseCaseGroupEntity useCaseGroupEntity = useCaseGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USE_CASE_GROUP));

        checkUseCaseByName(request);

        useCaseGroupEntity.setName(request.getName());
        useCaseGroupEntity.setDescription(request.getDescription());

        return useCaseGroupRepository.save(useCaseGroupEntity);
    }

    @Override
    public UseCaseGroupEntity addUseCase(Long useGroupId, AddUseCaseRequest request) {

        if (!useCaseGroupRepository.existsById(useGroupId)) {
            throw new NotFoundException(NOT_FOUND_USE_CASE_GROUP);
        }

//        List<UseCaseEntity> useCaseEntities = useCaseRepository
//                .findAllByIdIn(request.getUseCaseIds())
//                .stream()
//                .peek(it -> it.setUseCaseGroupId(useGroupId))
//                .collect(Collectors.toList());

//        useCaseRepository.saveAll(useCaseEntities);

        return useCaseGroupRepository.findById(useGroupId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USE_CASE_GROUP));
    }

    private void checkUseCaseByName(UseCaseGroupCreateRequest request) {

        if (useCaseGroupRepository.existsByName(request.getName())) {
            throw new DuplicatedException(EXISTED_USE_CASE_GROUP);
        }
    }
}
