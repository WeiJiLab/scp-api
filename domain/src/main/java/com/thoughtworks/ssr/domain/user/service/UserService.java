package com.thoughtworks.ssr.domain.user.service;

import com.thoughtworks.ssr.domain.user.exception.UserException;
import com.thoughtworks.ssr.domain.user.model.User;
import com.thoughtworks.ssr.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User saveUser(User user) {
        return userRepository.createUser(user)
                .orElseThrow(UserException::notFound);
    }

    public User updateUser(User user) {
        return userRepository.updateUser(user).orElseThrow(UserException::notFound);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(UserException::notFound);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserException::notFound);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserException::notFound);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }


}
