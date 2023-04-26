package com.thoughtworks.ssr.infrastructure.persistence.project.converter;

import com.thoughtworks.ssr.domain.project.model.K8sCluster;
import com.thoughtworks.ssr.domain.project.model.Project;
import com.thoughtworks.ssr.infrastructure.persistence.common.IEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.project.entity.K8sClusterEntity;
import com.thoughtworks.ssr.infrastructure.persistence.project.entity.ProjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface K8sClusterEntityConverter extends IEntityConverter<K8sCluster, K8sClusterEntity > {
}
