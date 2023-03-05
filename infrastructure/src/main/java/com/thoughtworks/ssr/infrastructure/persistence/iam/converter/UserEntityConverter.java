package com.thoughtworks.ssr.infrastructure.persistence.iam.converter;

import com.thoughtworks.ssr.domain.iam.model.User;
import com.thoughtworks.ssr.infrastructure.persistence.common.IEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.iam.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityConverter extends IEntityConverter<User, UserEntity> {
}
