package com.thoughtworks.ssr.infrastructure.persistence.common;

import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface IEntityConverter<Domain, Entity> {

    Entity from(Domain domain);

    default List<Entity> from(List<Domain> collection) {
        if (ObjectUtils.isEmpty(collection)) {
            return null;
        }
        return collection.stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    Domain toDomain(Entity entity);

    default List<Domain> toDomains(Collection<Entity> collection) {
        if (ObjectUtils.isEmpty(collection)) {
            return null;
        }

        return collection.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    default Page<Domain> toDomains(Page<Entity> page) {
        return page.map(this::toDomain);
    }
}
