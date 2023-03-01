package com.thoughtworks.ssr.infrastructure.persistence.imagescan.converter;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanStage;
import com.thoughtworks.ssr.infrastructure.persistence.common.IEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanStageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageScanStageEntityConverter extends IEntityConverter<ImageScanStage, ImageScanStageEntity> {
}
