package com.example.playlist_app.controller;

import com.example.playlist_app.dto.LoginRequest;
import com.example.playlist_app.model.User;
import com.example.playlist_app.security.JwtKeyGenerator;
import com.example.playlist_app.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = Jwts.builder()
                .setSubject(loginRequest.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_00))
                .signWith(JwtKeyGenerator.getSecretKey())
                .compact();

        return new ResponseEntity<>("Bearer " + token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
            return ResponseEntity.badRequest().body("Invalid input");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
