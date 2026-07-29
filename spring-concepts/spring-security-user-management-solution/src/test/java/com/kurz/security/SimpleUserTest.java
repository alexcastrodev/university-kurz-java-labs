package com.kurz.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SimpleUser")
class SimpleUserTest {

    @Test
    @DisplayName("should return the username from getUsername()")
    void shouldReturnUsername() {
        var authorities = List.of(new SimpleGrantedAuthority("READ"));
        var user = new SimpleUser("john", "password", authorities);

        assertEquals("john", user.getUsername());
    }

    @Test
    @DisplayName("should return the password from getPassword()")
    void shouldReturnPassword() {
        var authorities = List.of(new SimpleGrantedAuthority("READ"));
        var user = new SimpleUser("john", "secret123", authorities);

        assertEquals("secret123", user.getPassword());
    }

    @Test
    @DisplayName("should return the authorities from getAuthorities()")
    void shouldReturnAuthorities() {
        var authorities = List.of(
            new SimpleGrantedAuthority("READ"),
            new SimpleGrantedAuthority("WRITE")
        );
        var user = new SimpleUser("john", "password", authorities);

        assertEquals(authorities, user.getAuthorities());
    }

    @Test
    @DisplayName("should return true for isAccountNonExpired()")
    void shouldReturnTrueForIsAccountNonExpired() {
        var authorities = List.of(new SimpleGrantedAuthority("READ"));
        var user = new SimpleUser("john", "password", authorities);

        assertTrue(user.isAccountNonExpired());
    }

    @Test
    @DisplayName("should return true for isAccountNonLocked()")
    void shouldReturnTrueForIsAccountNonLocked() {
        var authorities = List.of(new SimpleGrantedAuthority("READ"));
        var user = new SimpleUser("john", "password", authorities);

        assertTrue(user.isAccountNonLocked());
    }

    @Test
    @DisplayName("should return true for isCredentialsNonExpired()")
    void shouldReturnTrueForIsCredentialsNonExpired() {
        var authorities = List.of(new SimpleGrantedAuthority("READ"));
        var user = new SimpleUser("john", "password", authorities);

        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    @DisplayName("should return true for isEnabled()")
    void shouldReturnTrueForIsEnabled() {
        var authorities = List.of(new SimpleGrantedAuthority("READ"));
        var user = new SimpleUser("john", "password", authorities);

        assertTrue(user.isEnabled());
    }
}