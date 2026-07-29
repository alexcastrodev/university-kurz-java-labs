package com.kurz.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * TODO-05 (optional): Decorator pattern wrapping a user entity with Spring Security concerns.
 * Separates persistence (JPA entity) from security (UserDetails interface).
 */
public class SecurityUser implements UserDetails {

    private final SimpleUser user;
    private final Collection<? extends GrantedAuthority> authorities;

    public SecurityUser(SimpleUser user) {
        this.user = user;
        this.authorities = user.getAuthorities();
    }

    public SecurityUser(SimpleUser user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

}