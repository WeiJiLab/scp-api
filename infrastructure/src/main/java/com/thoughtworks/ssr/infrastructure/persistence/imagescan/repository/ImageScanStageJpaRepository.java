package com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanStageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageScanStageJpaRepository extends JpaRepository<ImageScanStageEntity, Long> {

    Optional<ImageScanStageEntity> findAllByPjId(Long pjId);
}
