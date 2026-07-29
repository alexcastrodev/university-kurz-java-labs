# Spring Security User Management - Solution

## Overview

This is the official solution for the Spring Security User Management lab. It demonstrates how to implement the core Spring Security contracts for authentication and user management.

## Key Concepts

### UserDetails Interface

The `UserDetails` interface is the shape Spring Security expects a user to have:

- `getUsername()` - returns the user's identifier
- `getPassword()` - returns the encrypted password
- `getAuthorities()` - returns the user's privileges
- `isAccountNonExpired()`, `isAccountNonLocked()`, `isCredentialsNonExpired()`, `isEnabled()` - account status flags

All status methods return `true` to allow the account through.

### GrantedAuthority

Represents a single privilege. `SimpleGrantedAuthority` is the built-in implementation:

```java
var auth = new SimpleGrantedAuthority("READ");
```

### UserDetailsService

Read-only service that loads users by username. Used for authentication alone.

### UserDetailsManager

Extends `UserDetailsService` with user management operations:
- `createUser()` - add a new user
- `updateUser()` - modify an existing user
- `deleteUser()` - remove a user
- `changePassword()` - update credentials
- `userExists()` - check if user is present

## Implementation Details

### SimpleUser

A straightforward implementation storing username, password, and authorities:

```java
var user = new SimpleUser("john", "password", authorities);
```

### InMemoryUserDetailsService

Stores users in a `List<UserDetails>` and loads them by username:

```java
var service = new InMemoryUserDetailsService(users);
var user = service.loadUserByUsername("john");
```

## Account Status Flags

The four status methods control when an account can be used:

- `isAccountNonExpired()` - `true` if the account hasn't expired
- `isAccountNonLocked()` - `true` if the account isn't locked
- `isCredentialsNonExpired()` - `true` if the credentials haven't expired
- `isEnabled()` - `true` if the account is active

All must return `true` for authentication to succeed.

## Trade-offs and Best Practices

1. **Separation of concerns**: A JPA entity implementing `UserDetails` directly mixes persistence and security concerns — consider using a decorator pattern with `SecurityUser` wrapping the entity.

2. **Read-only service**: `UserDetailsService` is intentionally minimal—it only needs to load users for authentication. For user management (create, update, delete), Spring provides `UserDetailsManager` in additional modules.

3. **In-memory storage**: Perfect for testing and demos, but real applications use database-backed implementations like `JdbcUserDetailsManager`.

## Summary

- Implement `UserDetails` to represent a user in Spring Security
- Use `GrantedAuthority` to define privileges
- Implement `UserDetailsService` for authentication
- Understand account status flags for fine-grained control
- Keep security and persistence concerns separate