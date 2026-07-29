package com.kurz.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A simple implementation of UserDetails that stores username, password, and authorities.
 */
public class SimpleUser implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public SimpleUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        // TODO-00: Store the provided username, password, and authorities.

        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override public String getUsername() { return username;}

    @Override public String getPassword() { return password; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO-00: Return the authorities.
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO-01: Return true if the account is not expired.

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO-01: Return true if the account is not locked.

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO-01: Return true if the credentials are not expired.

        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO-01: Return true if the account is enabled.

        return true;
    }
}