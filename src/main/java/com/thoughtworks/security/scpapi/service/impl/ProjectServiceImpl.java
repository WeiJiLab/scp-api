package com.thoughtworks.security.scpapi.service.impl;

import com.thoughtworks.security.scpapi.entity.Application;
import com.thoughtworks.security.scpapi.entity.Project;
import com.thoughtworks.security.scpapi.exception.core.DuplicatedException;
import com.thoughtworks.security.scpapi.exception.core.NotFoundException;
import com.thoughtworks.security.scpapi.repository.ProjectRepository;
import com.thoughtworks.security.scpapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.thoughtworks.security.scpapi.exception.core.DuplicatedError.EXISTED_PROJECT;
import static com.thoughtworks.security.scpapi.exception.core.NotFoundError.NOT_FOUND_APPLICATION;
import static com.thoughtworks.security.scpapi.exception.core.NotFoundError.NOT_FOUND_PROJECT;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project create(Project project) {
        projectRepository.findByName(project.getName()).ifPresent(existedProject -> {
            throw new DuplicatedException(EXISTED_PROJECT);
        });

        return projectRepository.saveAndFlush(project);
    }

    @Override
    public Project findById(Long id) {
        return getProject(id);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project update(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public void delete(Long id) {
        try {
            projectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new NotFoundException(NOT_FOUND_PROJECT);
        }
    }

    @Override
    public List<Application> getApplications(Long id) {
        return getProject(id).getApplications();
    }

    @Override
    public Application getApplication(Long id, Long applicationId) {
        var project = getProject(id);
        return project.getApplications()
                .stream()
                .filter(application -> application.getId().equals(applicationId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_APPLICATION));
    }

    @Override
    public void deleteApplication(Long id, Long applicationId) {
        var project = getProject(id);
        project.removeApplicationById(applicationId);
        projectRepository.save(project);
    }

    private Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_PROJECT));
    }
}
