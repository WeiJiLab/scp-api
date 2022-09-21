package com.thoughtworks.ssr.infrastructure.persistence.usecases.adapter;

import com.thoughtworks.ssr.domain.usecase.model.UseCaseGroup;
import com.thoughtworks.ssr.domain.usecase.repository.UseCaseGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UseCaseGroupRepositoryAdapter implements UseCaseGroupRepository {
    @Override
    public UseCaseGroup create(UseCaseGroup useCase) {
        return null;
    }

    @Override
    public UseCaseGroup update(UseCaseGroup useCase) {
        return null;
    }

    @Override
    public Optional<UseCaseGroup> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Page<UseCaseGroup> pageUseCaseGroup(Pageable pageable) {
        return null;
    }
}
