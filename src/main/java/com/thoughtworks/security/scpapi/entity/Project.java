package com.thoughtworks.security.scpapi.entity;

import com.thoughtworks.security.scpapi.controller.request.CreateProjectRequest;
import com.thoughtworks.security.scpapi.exception.ApplicationNotFoundException;
import com.thoughtworks.security.scpapi.exception.DuplicatedApplicationException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long ownerId;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "projectId")
    private List<Application> applications = new ArrayList<>();

    public static Project from(CreateProjectRequest createProjectRequest) {
        return Project.builder()
                .name(createProjectRequest.getName())
                .description(createProjectRequest.getDescription())
                .ownerId(createProjectRequest.getOwnerId())
                .build();
    }

    public void addApplication(Application application) {
        var isApplicationNameExisted = applications.stream()
                .anyMatch(app -> app.getName().equals(application.getName()));
        if (isApplicationNameExisted) {
            throw new DuplicatedApplicationException();
        }
        applications.add(application);
    }

    public void removeApplicationById(Long applicationId) {
        var application = applications.stream()
                .filter(app -> app.getId().equals(applicationId))
                .findFirst().orElseThrow(ApplicationNotFoundException::new);
        applications.remove(application);
    }
}
