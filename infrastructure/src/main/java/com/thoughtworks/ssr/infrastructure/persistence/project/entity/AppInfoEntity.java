package com.thoughtworks.ssr.infrastructure.persistence.project.entity;

import com.thoughtworks.ssr.domain.core.enums.RepoType;
import com.thoughtworks.ssr.infrastructure.persistence.common.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "app_info")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppInfoEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    private String name;

    private String description;

    private String repo;

    private String branch;

    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private RepoType repoType;

    private String codePath;

    private String workdir;
}
