package com.thoughtworks.ssr.domain.project.service;

import com.thoughtworks.ssr.domain.project.exception.ApplicationException;
import com.thoughtworks.ssr.domain.project.model.Application;
import com.thoughtworks.ssr.domain.project.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public Application create(Application application) {
        return applicationRepository.create(application);
    }

    public Application update(Application application) {
        return applicationRepository.update(application);
    }

    public Application findById(Long id) {
        return applicationRepository.findById(id).orElseThrow(ApplicationException::notFound);
    }
}
