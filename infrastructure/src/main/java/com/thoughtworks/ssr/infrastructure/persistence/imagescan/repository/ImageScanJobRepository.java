package com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageScanJobRepository extends JpaRepository<ImageScanRequestEntity, Long> {

    @Override
    <S extends ImageScanRequestEntity> S save(S entity);
}
