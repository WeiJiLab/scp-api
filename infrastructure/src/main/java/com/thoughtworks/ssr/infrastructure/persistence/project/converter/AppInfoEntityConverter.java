package com.thoughtworks.ssr.infrastructure.persistence.project.converter;

import com.thoughtworks.ssr.domain.project.model.AppInfo;
import com.thoughtworks.ssr.infrastructure.persistence.common.IEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.project.entity.AppInfoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppInfoEntityConverter extends IEntityConverter<AppInfo, AppInfoEntity> {
}
