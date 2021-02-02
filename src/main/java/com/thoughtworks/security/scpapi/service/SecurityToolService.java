package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.entity.SecurityTool;

import java.util.List;

public interface SecurityToolService {

    SecurityTool create(SecurityTool securityTool);

    SecurityTool findById(Integer id);

    List<SecurityTool> findAll();
}
