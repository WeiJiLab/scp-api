package com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageScanBusinessRepository extends JpaRepository<ImageScanResultEntity, String> {
    @Query(value = "select * from image_scan_result where pj_id = ?", nativeQuery = true)
    ImageScanResultEntity findResultById(String pj_id);
}
