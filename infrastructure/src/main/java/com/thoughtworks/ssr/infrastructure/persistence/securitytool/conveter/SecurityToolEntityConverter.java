package com.thoughtworks.ssr.infrastructure.persistence.securitytool.conveter;

import com.thoughtworks.ssr.domain.securitytool.model.SecurityTool;
import com.thoughtworks.ssr.infrastructure.persistence.common.IEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.securitytool.entity.SecurityToolEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SecurityToolEntityConverter extends IEntityConverter<SecurityTool, SecurityToolEntity> {
}
