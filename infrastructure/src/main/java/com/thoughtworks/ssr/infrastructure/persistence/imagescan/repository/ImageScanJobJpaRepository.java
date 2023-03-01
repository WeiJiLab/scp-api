package com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ImageScanJobJpaRepository extends
        JpaRepository<ImageScanCommandEntity, Long>,
        JpaSpecificationExecutor<ImageScanCommandEntity> {

    List<ImageScanCommandEntity> findAll();

    Optional<ImageScanCommandEntity> findById(Long id);
}
