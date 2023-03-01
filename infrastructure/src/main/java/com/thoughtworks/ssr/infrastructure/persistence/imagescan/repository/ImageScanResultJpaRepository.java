package com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ImageScanResultJpaRepository extends
        JpaRepository<ImageScanResultEntity, Long>,
        JpaSpecificationExecutor<ImageScanResultEntity> {
    List<ImageScanResultEntity> findAllByPjId(Long pjId);
}
