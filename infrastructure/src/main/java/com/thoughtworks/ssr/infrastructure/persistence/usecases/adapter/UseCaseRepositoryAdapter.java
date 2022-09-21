package com.thoughtworks.ssr.infrastructure.persistence.usecases.adapter;

import com.thoughtworks.ssr.domain.usecase.model.UseCase;
import com.thoughtworks.ssr.domain.usecase.repository.UseCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UseCaseRepositoryAdapter implements UseCaseRepository {
    @Override
    public UseCase create(UseCase useCase) {
        return null;
    }

    @Override
    public UseCase update(UseCase useCase) {
        return null;
    }

    @Override
    public Optional<UseCase> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<UseCase> pageUseCase(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
