package com.kurz.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InMemoryUserDetailsService")
class InMemoryUserDetailsServiceTest {

    @Test
    @DisplayName("should load a user by username")
    void shouldLoadUserByUsername() {
        var user = new SimpleUser("john", "password", List.of(new SimpleGrantedAuthority("READ")));
        var service = new InMemoryUserDetailsService(List.of(user));

        UserDetails loaded = service.loadUserByUsername("john");

        assertEquals("john", loaded.getUsername());
        assertEquals("password", loaded.getPassword());
    }

    @Test
    @DisplayName("should throw UsernameNotFoundException when user is not found")
    void shouldThrowUsernameNotFoundExceptionWhenUserNotFound() {
        var user = new SimpleUser("john", "password", List.of(new SimpleGrantedAuthority("READ")));
        var service = new InMemoryUserDetailsService(List.of(user));

        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("unknown"));
    }

    @Test
    @DisplayName("should load the correct user when multiple users exist")
    void shouldLoadCorrectUserWhenMultipleUsersExist() {
        var user1 = new SimpleUser("alice", "pass1", List.of(new SimpleGrantedAuthority("READ")));
        var user2 = new SimpleUser("bob", "pass2", List.of(new SimpleGrantedAuthority("WRITE")));
        var service = new InMemoryUserDetailsService(List.of(user1, user2));

        UserDetails loaded = service.loadUserByUsername("bob");

        assertEquals("bob", loaded.getUsername());
        assertEquals("pass2", loaded.getPassword());
    }
}