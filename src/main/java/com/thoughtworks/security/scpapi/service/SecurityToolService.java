package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.domain.SecurityTool;

import java.util.List;

public interface SecurityToolService {

    SecurityTool create(SecurityTool securityTool);

    SecurityTool findById(Long id);

    List<SecurityTool> findAll();
}
