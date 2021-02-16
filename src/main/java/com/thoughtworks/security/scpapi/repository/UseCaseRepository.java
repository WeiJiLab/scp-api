package com.thoughtworks.security.scpapi.repository;

import com.thoughtworks.security.scpapi.entity.UseCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UseCaseRepository extends JpaRepository<UseCaseEntity, Long> {

    void deleteAllByIdIn(Collection<Long> id);

    List<UseCaseEntity> findAllByIdIn(Collection<Long> id);

    List<UseCaseEntity> findAllBySecurityToolId(Long securityToolId);


}
