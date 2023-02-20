package com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanStageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageScanStageRepository extends JpaRepository<ImageScanStageEntity, String> {
    @Query(value = "select * from image_scan_steps where pj_id = ?", nativeQuery = true)
    ImageScanStageEntity findStageById(String pj_id);
}
