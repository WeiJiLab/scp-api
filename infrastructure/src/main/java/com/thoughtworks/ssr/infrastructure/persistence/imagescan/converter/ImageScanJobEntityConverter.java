package com.thoughtworks.ssr.infrastructure.persistence.imagescan.converter;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanCommand;
import com.thoughtworks.ssr.infrastructure.persistence.common.IEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanCommandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageScanJobEntityConverter extends IEntityConverter<ImageScanCommand, ImageScanCommandEntity> {
}
