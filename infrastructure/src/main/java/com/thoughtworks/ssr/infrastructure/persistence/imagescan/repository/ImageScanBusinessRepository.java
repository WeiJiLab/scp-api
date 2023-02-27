package com.thoughtworks.ssr.infrastructure.persistence.imagescan.repository;

import com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity.ImageScanStageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageScanBusinessRepository extends JpaRepository<ImageScanStageEntity, Long> {
    @Query(value = "select * from image_scan_steps where pj_id=?", nativeQuery = true)
    ImageScanStageEntity[] findResultById(Long pj_id);

    @Override
    <S extends ImageScanStageEntity> S save(S entity);
}
