package com.thoughtworks.ssr.domain.usecase.repository;

import com.thoughtworks.ssr.domain.usecase.model.UseCaseGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UseCaseGroupRepository {
    UseCaseGroup create(UseCaseGroup useCase);

    UseCaseGroup update(UseCaseGroup useCase);

    Optional<UseCaseGroup> findById(Long id);

    void deleteById(Long id);

    Page<UseCaseGroup> pageUseCaseGroup(Pageable pageable);
}
