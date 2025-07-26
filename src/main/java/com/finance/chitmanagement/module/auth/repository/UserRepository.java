package com.finance.chitmanagement.module.auth.repository;

import com.finance.chitmanagement.module.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
//    Optional<User> findByEmail(String email);
//    boolean existsByEmail(String email);
}
