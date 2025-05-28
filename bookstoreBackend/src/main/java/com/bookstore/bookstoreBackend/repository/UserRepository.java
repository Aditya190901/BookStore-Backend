package com.bookstore.bookstoreBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookstore.bookstoreBackend.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}