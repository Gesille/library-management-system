package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import com.example.library.config.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");

        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<?> response = authController.register(user);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User registered successfully!", response.getBody());
    }

    @Test
    void testLoginUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken(String.valueOf(user))).thenReturn("mockToken");

        ResponseEntity<?> response = authController.login((com.example.library.dto.LoginRequest) Map.of("username", "testUser", "password", "password"));

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(((Map<?, ?>) response.getBody()).get("token"));
    }
}
