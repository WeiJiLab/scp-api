package com.thoughtworks.ssr.domain.usecase.service;

import com.thoughtworks.ssr.domain.usecase.exception.UseCaseGroupException;
import com.thoughtworks.ssr.domain.usecase.model.UseCaseGroup;
import com.thoughtworks.ssr.domain.usecase.repository.UseCaseGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UseCaseGroupService {
    private final UseCaseGroupRepository useCaseGroupRepository;

    public UseCaseGroup create(UseCaseGroup useCase) {
        return useCaseGroupRepository.create(useCase);
    }

    public UseCaseGroup update(UseCaseGroup useCase) {
        return useCaseGroupRepository.update(useCase);
    }

    public UseCaseGroup findById(Long id) {
        return useCaseGroupRepository.findById(id).orElseThrow(UseCaseGroupException::notFound);
    }

    public void deleteById(Long id) {
        useCaseGroupRepository.deleteById(id);
    }

    public Page<UseCaseGroup> pageUseCaseGroup(Pageable pageable) {
        return useCaseGroupRepository.pageUseCaseGroup(pageable);
    }
}
