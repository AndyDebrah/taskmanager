package com.example.taskmanager.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.model.User;
import com.example.taskmanager.security.JwtUtil;
import com.example.taskmanager.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        String u = body.get("username");
        String p = body.get("password");
        User user = userService.register(u, p);
        return ResponseEntity.ok(Map.of("username", user.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String u = body.get("username");
        String p = body.get("password");
        return userService.findByUsername(u)
                .filter(user -> userService.checkPassword(user, p))
                .map(user -> ResponseEntity.ok(Map.of("token", jwtUtil.generateToken(user.getUsername()))))
                .orElse(ResponseEntity.status(401).body(Map.of("error", "invalid")));
    }
}
