package com.thoughtworks.ssr.domain.securitytool.repository;

import com.thoughtworks.ssr.domain.securitytool.model.SecurityTool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SecurityToolRepository {
    SecurityTool create(SecurityTool securityTool);

    Page<SecurityTool> pageSecurityTool(Pageable pageable);

    Optional<SecurityTool> findById(Long id);
}
