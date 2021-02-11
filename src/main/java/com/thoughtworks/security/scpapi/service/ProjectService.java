package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.entity.Application;
import com.thoughtworks.security.scpapi.entity.Project;

import java.util.List;

public interface ProjectService {

    Project create(Project project);

    Project findById(Long id);

    List<Project> findAll();

    Project update(Project project);

    void delete(Long id);

    List<Application> getApplications(Long id);

    Application getApplication(Long id, Long applicationId);

    void deleteApplication(Long id, Long applicationId);
}
