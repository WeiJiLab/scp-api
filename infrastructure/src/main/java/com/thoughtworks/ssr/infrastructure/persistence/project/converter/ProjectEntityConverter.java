package com.thoughtworks.ssr.infrastructure.persistence.project.converter;

import com.thoughtworks.ssr.domain.project.model.Project;
import com.thoughtworks.ssr.infrastructure.persistence.common.IEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.project.entity.ProjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectEntityConverter extends IEntityConverter<Project, ProjectEntity> {
}
