package com.thoughtworks.ssr.infrastructure.persistence.securitytool.adapter;

import com.thoughtworks.ssr.domain.securitytool.model.SecurityTool;
import com.thoughtworks.ssr.domain.securitytool.repository.SecurityToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityToolRepositoryAdapter implements SecurityToolRepository {
    @Override
    public SecurityTool create(SecurityTool securityTool) {
        return null;
    }

    @Override
    public Page<SecurityTool> pageSecurityTool(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<SecurityTool> findById(Long id) {
        return Optional.empty();
    }
}
