package com.thoughtworks.security.scpapi.service.impl;

import com.thoughtworks.security.scpapi.controller.request.AddUseCaseRequest;
import com.thoughtworks.security.scpapi.controller.request.UseCaseGroupCreateRequest;
import com.thoughtworks.security.scpapi.entity.UseCaseEntity;
import com.thoughtworks.security.scpapi.entity.UseCaseGroupEntity;
import com.thoughtworks.security.scpapi.exception.DuplicatedUseCaseGroupException;
import com.thoughtworks.security.scpapi.exception.UseCaseGroupNotFoundException;
import com.thoughtworks.security.scpapi.repository.UseCaseGroupRepository;
import com.thoughtworks.security.scpapi.repository.UseCaseRepository;
import com.thoughtworks.security.scpapi.service.UseCaseGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public UseCaseGroupEntity findById(Integer id) {
        return useCaseGroupRepository.findById(id)
                .orElseThrow(UseCaseGroupNotFoundException::new);
    }

    @Override
    public List<UseCaseGroupEntity> findAll() {
        return useCaseGroupRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        try {
            useCaseGroupRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new UseCaseGroupNotFoundException();
        }
    }

    @Override
    public UseCaseGroupEntity update(Integer id, UseCaseGroupCreateRequest request) {

        UseCaseGroupEntity useCaseGroupEntity = useCaseGroupRepository.findById(id)
                .orElseThrow(UseCaseGroupNotFoundException::new);

        checkUseCaseByName(request);

        useCaseGroupEntity.setName(request.getName());
        useCaseGroupEntity.setDescription(request.getDescription());

        return useCaseGroupRepository.save(useCaseGroupEntity);
    }

    @Override
    public UseCaseGroupEntity addUseCase(Integer useGroupId, AddUseCaseRequest request) {

        if (!useCaseGroupRepository.existsById(useGroupId)) {
            throw new UseCaseGroupNotFoundException();
        }

//        List<UseCaseEntity> useCaseEntities = useCaseRepository
//                .findAllByIdIn(request.getUseCaseIds())
//                .stream()
//                .peek(it -> it.setUseCaseGroupId(useGroupId))
//                .collect(Collectors.toList());

//        useCaseRepository.saveAll(useCaseEntities);

        return useCaseGroupRepository.findById(useGroupId)
                .orElseThrow(UseCaseGroupNotFoundException::new);
    }

    private void checkUseCaseByName(UseCaseGroupCreateRequest request) {

        if (useCaseGroupRepository.existsByName(request.getName())) {
            throw new DuplicatedUseCaseGroupException();
        }
    }
}
