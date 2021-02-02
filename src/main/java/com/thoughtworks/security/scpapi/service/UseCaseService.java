package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.dto.ComplianceUseCaseInputDto;
import com.thoughtworks.security.scpapi.entity.UseCaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UseCaseService {
    UseCaseEntity addUseCase(MultipartFile file, String description, String name, Integer toolId);

    void deleteUseCase(List<Long> useCaseIds);

    void modifyUseCase(ComplianceUseCaseInputDto useCaseInputDto);


    void deleteById(Long id);

    UseCaseEntity findById(Long id);

    UseCaseEntity update(Long id, MultipartFile file, String description, String name, Integer toolId);

    List<UseCaseEntity> findAll();

    List<UseCaseEntity> findUseCaseByToolId(Integer id);
}
