package com.thoughtworks.ssr.infrastructure.persistence.securitytool.entity;

import com.thoughtworks.ssr.domain.securitytool.model.SecurityToolCategory;
import com.thoughtworks.ssr.infrastructure.persistence.common.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
