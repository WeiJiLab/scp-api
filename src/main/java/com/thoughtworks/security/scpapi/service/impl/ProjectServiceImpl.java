package com.thoughtworks.security.scpapi.service.impl;

import com.thoughtworks.security.scpapi.entity.Application;
import com.thoughtworks.security.scpapi.entity.Project;
import com.thoughtworks.security.scpapi.exception.ApplicationNotFoundException;
import com.thoughtworks.security.scpapi.exception.DuplicatedProjectException;
import com.thoughtworks.security.scpapi.exception.ProjectNotFoundException;
import com.thoughtworks.security.scpapi.repository.ProjectRepository;
import com.thoughtworks.security.scpapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project create(Project project) {
        projectRepository.findByName(project.getName()).ifPresent(existedProject -> {
            throw new DuplicatedProjectException();
        });

        return projectRepository.saveAndFlush(project);
    }

    @Override
    public Project findById(Integer id) {
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
    public void delete(Integer id) {
        try {
            projectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new ProjectNotFoundException();
        }
    }

    @Override
    public List<Application> getApplications(Integer id) {
        return getProject(id).getApplications();
    }

    @Override
    public Application getApplication(Integer id, Integer applicationId) {
        var project = getProject(id);
        return project.getApplications()
                .stream()
                .filter(application -> application.getId().equals(applicationId))
                .findFirst()
                .orElseThrow(ApplicationNotFoundException::new);
    }

    @Override
    public void deleteApplication(Integer id, Integer applicationId) {
        var project = getProject(id);
        project.removeApplicationById(applicationId);
        projectRepository.save(project);
    }

    private Project getProject(Integer id) {
        return projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
    }
}
