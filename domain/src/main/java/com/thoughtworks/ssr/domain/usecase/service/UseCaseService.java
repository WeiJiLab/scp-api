package com.thoughtworks.ssr.domain.usecase.service;

import com.thoughtworks.ssr.domain.usecase.exception.UseCaseException;
import com.thoughtworks.ssr.domain.usecase.repository.UseCaseRepository;
import com.thoughtworks.ssr.domain.usecase.model.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UseCaseService {
    private final UseCaseRepository useCaseRepository;

    public UseCase create(UseCase useCase) {
        return useCaseRepository.create(useCase);
    }

    public UseCase update(UseCase useCase) {
        return useCaseRepository.update(useCase);
    }

    public UseCase findById(Long id) {
        return useCaseRepository.findById(id).orElseThrow(UseCaseException::notFound);
    }

    public Page<UseCase> pageUseCase(Pageable pageable) {
        return useCaseRepository.pageUseCase(pageable);
    }

    public void deleteById(Long id) {
        useCaseRepository.deleteById(id);
    }
}
