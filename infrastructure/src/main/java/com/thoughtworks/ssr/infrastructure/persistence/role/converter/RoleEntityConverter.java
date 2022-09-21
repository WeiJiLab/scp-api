package com.thoughtworks.ssr.infrastructure.persistence.role.converter;

import com.thoughtworks.ssr.domain.role.model.Role;
import com.thoughtworks.ssr.infrastructure.persistence.role.entity.RoleEntity;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleEntityConverter {

    public static RoleEntity convertFrom(Role role) {
        if (ObjectUtils.isEmpty(role)) {
            return null;
        }
        return RoleEntity.builder()
                .id(role.getId())
                .role(role.getRole())
                .build();
    }

    public static Set<RoleEntity> convertFrom(Set<Role> role) {
        if (ObjectUtils.isEmpty(role)) {
            return Set.of();
        }

        return role.stream()
                .map(RoleEntityConverter::convertFrom)
                .collect(Collectors.toSet());
    }

    public static Role convertTo(RoleEntity roleEntity) {
        if (ObjectUtils.isEmpty(roleEntity)) {
            return null;
        }
        return Role.builder()
                .id(roleEntity.getId())
                .role(roleEntity.getRole())
                .build();
    }

    public static Set<Role> convertTo(Collection<RoleEntity> roleEntities) {
        if (ObjectUtils.isEmpty(roleEntities)) {
            return Set.of();
        }
        return roleEntities.stream()
                .map(RoleEntityConverter::convertTo)
                .collect(Collectors.toSet());
    }
}
