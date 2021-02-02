package com.thoughtworks.security.scpapi.repository;

import com.thoughtworks.security.scpapi.entity.SecurityReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityReportRepository extends JpaRepository<SecurityReportEntity, Long> {

}
