package com.thoughtworks.ssr.infrastructure.persistence.project.adapter;

import com.thoughtworks.ssr.domain.project.model.Application;
import com.thoughtworks.ssr.domain.project.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ApplicationRepositoryAdapter implements ApplicationRepository {
    @Override
    public Application create(Application application) {
        return null;
    }

    @Override
    public Application update(Application application) {
        return null;
    }

    @Override
    public Optional<Application> findById(Long id) {
        return Optional.empty();
    }
}
