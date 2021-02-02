package com.thoughtworks.security.scpapi.repository;


import com.thoughtworks.security.scpapi.entity.SecurityResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SecurityResultRepository extends JpaRepository<SecurityResult, Long> {
    @Query(nativeQuery = true, value = "select * from security_scan_result")
    List<SecurityResult> findAllNativeSql();
    List<SecurityResult> findAllByResult(Integer result);

    @Query(value = "update security_scan_result set result=?2 where id=?1")
    @Modifying
    @Transactional
    public void updateState(Long id, Integer state);
}
