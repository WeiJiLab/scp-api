package com.thoughtworks.ssr.infrastructure.persistence.iam.adapter;

import com.thoughtworks.ssr.domain.iam.model.User;
import com.thoughtworks.ssr.domain.iam.repository.UserRepository;
import com.thoughtworks.ssr.infrastructure.persistence.iam.converter.UserEntityConverter;
import com.thoughtworks.ssr.infrastructure.persistence.iam.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserEntityConverter entityConverter;

    @Override
    public Optional<User> createUser(User user) {
        var newUser = userJpaRepository.saveAndFlush(entityConverter.from(user));
        return Optional.of(entityConverter.toDomain(newUser));
    }

    @Override
    public Optional<User> updateUser(User user) {
        var updatedUser = userJpaRepository.save(entityConverter.from(user));
        return Optional.of(entityConverter.toDomain(updatedUser));
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(entityConverter::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(entityConverter::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(entityConverter::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }
}
