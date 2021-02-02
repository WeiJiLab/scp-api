package com.thoughtworks.security.scpapi.controller;

import com.thoughtworks.security.scpapi.controller.request.CreateApplicationRequest;
import com.thoughtworks.security.scpapi.controller.request.CreateProjectRequest;
import com.thoughtworks.security.scpapi.entity.Application;
import com.thoughtworks.security.scpapi.entity.Project;
import com.thoughtworks.security.scpapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @PostMapping
    public ResponseEntity<Project> create(@Validated @RequestBody CreateProjectRequest createProjectRequest) {
        var project = service.create(Project.from(createProjectRequest));
        return status(CREATED).body(project);
    }

    @GetMapping("{id}")
    public ResponseEntity<Project> findById(@PathVariable Integer id) {
        var project = service.findById(id);
        return status(OK).body(project);
    }

    @GetMapping
    public ResponseEntity<List<Project>> findAll() {
        var projects = service.findAll();
        return status(OK).body(projects);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        service.delete(id);
        return status(NO_CONTENT).build();
    }

    @PostMapping("{id}/applications")
    public ResponseEntity<Application> createApplication(@PathVariable Integer id,
                                                         @Validated @RequestBody CreateApplicationRequest createApplicationRequest) {
        var project = service.findById(id);
        var application = Application.from(createApplicationRequest);
        application.setProjectId(id);
        project.addApplication(application);
        var updatedProject = service.update(project);
        var createdApplication = updatedProject.getApplications()
                .stream()
                .filter(app -> app.getName().equals(createApplicationRequest.getName()))
                .findFirst()
                .orElseThrow();
        return status(CREATED).body(createdApplication);
    }

    @GetMapping("{id}/applications")
    public ResponseEntity<List<Application>> getApplications(@PathVariable Integer id) {
        return status(OK).body(service.getApplications(id));
    }

    @GetMapping("{id}/applications/{applicationId}")
    public ResponseEntity<Application> getApplication(@PathVariable Integer id, @PathVariable Integer applicationId) {
        return status(OK).body(service.getApplication(id, applicationId));
    }

    @DeleteMapping("{id}/applications/{applicationId}")
    public ResponseEntity deleteApplication(@PathVariable Integer id, @PathVariable Integer applicationId) {
        service.deleteApplication(id, applicationId);
        return status(NO_CONTENT).build();
    }
}
