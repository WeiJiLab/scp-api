package com.thoughtworks.ssr.infrastructure.persistence.securitytool.entity;

import com.thoughtworks.ssr.domain.securitytool.model.SecurityToolCategory;
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

@Entity(name = "security_tool")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityToolEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SecurityToolCategory category;

    private String description;

    private String metadata;
}
