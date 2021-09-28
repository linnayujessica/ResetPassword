package com.webapp.resetPassword;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncoderTest {

    @Test
    public void encodePassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = "$2a$10$E09tSXe/SxhKZotvMuyPyeEYcy7kIz4xLqD/dfFKzgIwruU265oxu";

        boolean match = passwordEncoder.matches("123456Abc", encodedPassword);
        assertTrue(match);
    }
}
