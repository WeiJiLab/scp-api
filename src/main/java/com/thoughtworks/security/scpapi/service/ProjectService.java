package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.entity.Application;
import com.thoughtworks.security.scpapi.entity.Project;

import java.util.List;

public interface ProjectService {

    Project create(Project project);

    Project findById(Integer id);

    List<Project> findAll();

    Project update(Project project);

    void delete(Integer id);

    List<Application> getApplications(Integer id);

    Application getApplication(Integer id, Integer applicationId);

    void deleteApplication(Integer id, Integer applicationId);
}
