package com.thoughtworks.security.scpapi.service;

import com.thoughtworks.security.scpapi.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByName(String name);
    Optional<User> save(String saml);
    void delete(String username);
}
