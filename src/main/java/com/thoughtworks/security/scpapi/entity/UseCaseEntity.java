package com.thoughtworks.security.scpapi.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@Entity(name = "use_case")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UseCaseEntity extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long securityToolId;

    private String scriptPath;

//    @ManyToMany(mappedBy = "useCases")
//    private List<UseCaseGroupEntity> useCaseGroups = new ArrayList<>();
}
