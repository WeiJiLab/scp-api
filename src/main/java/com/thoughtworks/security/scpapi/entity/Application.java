package com.thoughtworks.security.scpapi.entity;

import com.thoughtworks.security.scpapi.controller.request.CreateApplicationRequest;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer projectId;

    private String name;

    private String description;

    private Integer ownerId;

    public static Application from(CreateApplicationRequest createApplicationRequest) {
        return Application.builder()
                .name(createApplicationRequest.getName())
                .description(createApplicationRequest.getDescription())
                .ownerId(createApplicationRequest.getOwnerId())
                .build();
    }
}
