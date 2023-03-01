package com.thoughtworks.ssr.infrastructure.persistence.imagescan.converter;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanResult;
import com.thoughtworks.ssr.infrastructure.persistence.common.IEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanResultEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageScanResultEntityConverter extends IEntityConverter<ImageScanResult, ImageScanResultEntity> {
}
