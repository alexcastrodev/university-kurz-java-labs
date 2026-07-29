package com.kurz.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * TODO-04 (optional): Example demonstrating multiple authorities and privilege levels.
 * Shows how to create users with different role and permission combinations.
 */
public class AuthorityExample {

    /**
     * Example: Basic user with USER role
     */
    public static UserDetails createBasicUser() {
        Collection<GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority("ROLE_USER"),
            new SimpleGrantedAuthority("READ")
        );
        return new SimpleUser("john", "password123", authorities);
    }

    /**
     * Example: Admin user with elevated privileges
     */
    public static UserDetails createAdminUser() {
        Collection<GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority("ROLE_ADMIN"),
            new SimpleGrantedAuthority("ROLE_USER"),
            new SimpleGrantedAuthority("READ"),
            new SimpleGrantedAuthority("WRITE"),
            new SimpleGrantedAuthority("DELETE")
        );
        return new SimpleUser("admin", "admin123", authorities);
    }

    /**
     * Example: Moderator with intermediate privileges
     */
    public static UserDetails createModeratorUser() {
        Collection<GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority("ROLE_MODERATOR"),
            new SimpleGrantedAuthority("ROLE_USER"),
            new SimpleGrantedAuthority("READ"),
            new SimpleGrantedAuthority("WRITE"),
            new SimpleGrantedAuthority("MODERATE")
        );
        return new SimpleUser("moderator", "mod123", authorities);
    }

    /**
     * Example: Guest user with minimal privileges
     */
    public static UserDetails createGuestUser() {
        Collection<GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority("ROLE_GUEST"),
            new SimpleGrantedAuthority("READ")
        );
        return new SimpleUser("guest", "guest123", authorities);
    }

}