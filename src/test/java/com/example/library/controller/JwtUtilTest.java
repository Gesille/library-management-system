package com.example.library.controller;

import com.example.library.config.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil("mySecretKeyThatIsAtLeast32CharactersLong", 1000 * 60 * 60);
    }

    @Test
    void testGenerateAndValidateToken() {
        UserDetails userDetails = new User("testUser", "password", Mockito.mock(List.class));
        String token = jwtUtil.generateToken(userDetails.getUsername());

        assertNotNull(token);
        assertEquals("testUser", jwtUtil.extractUsername(token));
    }

    @Test
    void testTokenIsValid() {
        UserDetails userDetails = new User("testUser", "password", Mockito.mock(List.class));
        String token = jwtUtil.generateToken(userDetails.getUsername());

        // ✅ التحقق من أن التوكن صالح للمستخدم الحالي
        assertTrue(jwtUtil.validateToken(token));
    }
}
