package com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ImageScanJobJpaRepository extends
        JpaRepository<ImageScanCommandEntity, Long>,
        JpaSpecificationExecutor<ImageScanCommandEntity> {

    Optional<ImageScanCommandEntity> findById(Long id);
}
