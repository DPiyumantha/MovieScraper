package com.dimalka.authorizationservernew.repository;

import com.dimalka.authorizationservernew.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String name);
    Optional<User> findByEmail(String email);
}
