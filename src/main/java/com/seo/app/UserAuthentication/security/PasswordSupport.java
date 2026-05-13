package com.seo.app.UserAuthentication.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Encodes new passwords with BCrypt and verifies both modern hashes and legacy plaintext
 * stored in the database until migrated.
 */
@Component
public class PasswordSupport {

    private static final String BCRYPT_PREFIX = "$2a$";

    private final PasswordEncoder passwordEncoder;

    public PasswordSupport(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String storedPassword) {
        if (storedPassword == null || rawPassword == null) {
            return false;
        }
        if (storedPassword.startsWith(BCRYPT_PREFIX)) {
            return passwordEncoder.matches(rawPassword, storedPassword);
        }
        return rawPassword.equals(storedPassword);
    }
}
