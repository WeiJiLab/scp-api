package com.thoughtworks.ssr.domain.usecase.repository;

import com.thoughtworks.ssr.domain.usecase.model.UseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UseCaseRepository {
    UseCase create(UseCase useCase);

    UseCase update(UseCase useCase);

    Optional<UseCase> findById(Long id);

    Page<UseCase> pageUseCase(Pageable pageable);

    void deleteById(Long id);
}
