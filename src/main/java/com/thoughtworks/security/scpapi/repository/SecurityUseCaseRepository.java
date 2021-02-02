package com.thoughtworks.security.scpapi.repository;
import com.thoughtworks.security.scpapi.entity.SecurityUseCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SecurityUseCaseRepository extends JpaRepository<SecurityUseCase, Long> {
    @Modifying
    @Transactional
    @Query("delete from security_usecase tab where tab.id in ?1")
    int deleteByUseCaseId(Long[] ids);
    @Modifying
    @Transactional
    int deleteByIdIn(Long[] ids);
}
