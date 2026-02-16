package com.example.taskmanager.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) { this.repo = repo; }

    public User register(String username, String rawPassword) {
        if (repo.findByUsername(username).isPresent()) throw new IllegalArgumentException("username exists");
        User u = new User(username, encoder.encode(rawPassword));
        return repo.save(u);
    }

    public Optional<User> findByUsername(String username) { return repo.findByUsername(username); }

    public boolean checkPassword(User user, String rawPassword) {
        return encoder.matches(rawPassword, user.getPassword());
    }
}
