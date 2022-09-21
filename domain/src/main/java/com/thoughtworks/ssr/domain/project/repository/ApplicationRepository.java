package com.thoughtworks.ssr.domain.project.repository;

import com.thoughtworks.ssr.domain.project.model.Application;

import java.util.Optional;

public interface ApplicationRepository {
    Application create(Application application);

    Application update(Application application);

    Optional<Application> findById(Long id);
}
