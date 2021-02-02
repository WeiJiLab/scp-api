//package com.thoughtworks.security.scpapi.repository;
//
//import com.thoughtworks.security.scpapi.entity.ComplianceResultEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.transaction.annotation.Transactional;
//
//public interface ComplianceResultRepository extends JpaRepository<ComplianceResultEntity, Long> {
//    @Query(value = "update scan_result set result=?2, result_path=?3 where id=?1")
//    @Modifying
//    @Transactional
//    public void updateState(Long id, Integer state, String reportName);
//}
