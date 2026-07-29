# Spring Security User Management

## Goal

Understand how Spring Security represents a user and the contracts that define user authentication and authorization in a Spring application.

## Prerequisites

- Basic Spring Framework knowledge
- Understanding of interfaces and implementations in Java
- Familiarity with authentication and authorization concepts

## Task

Implement the core Spring Security contracts for representing and loading users:

- `UserDetails` - represents a user with credentials and account status flags
- `GrantedAuthority` - represents a single privilege or authority
- `UserDetailsService` - loads users by username for authentication

## Instructions

Complete the following TODOs:

- TODO-00: Implement the `UserDetails` interface with all required methods
- TODO-01: Understand account status flags (expired, locked, credentials expired, disabled)
- TODO-02: Implement a `UserDetailsService` to load users by username

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl spring-concepts/spring-security-user-management test
```

Or from the lab directory:

```bash
cd spring-concepts/spring-security-user-management
mvn test
```

## Bonus

- BONUS-00: Use the `User` builder class from Spring Security for quick prototyping
- BONUS-01: Create multiple authorities and explore different privilege levels
- BONUS-02: Implement a decorator pattern with `SecurityUser` wrapping a JPA entity