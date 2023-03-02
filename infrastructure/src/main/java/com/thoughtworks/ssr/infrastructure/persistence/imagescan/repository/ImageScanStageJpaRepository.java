package com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanStageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageScanStageJpaRepository extends JpaRepository<ImageScanStageEntity, Long> {

    List<ImageScanStageEntity> findAllByProjectId(Long pjId);
}
