package com.kurz.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * An in-memory implementation of UserDetailsService that stores users in a list.
 */
public class InMemoryUserDetailsService implements UserDetailsService {

    private final List<UserDetails> users;

    public InMemoryUserDetailsService(List<UserDetails> users) {
        // TODO-02: Store the provided list of users.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO-02: Search for a user with the given username in the users list.
        // If found, return the user. If not found, throw UsernameNotFoundException.

        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
