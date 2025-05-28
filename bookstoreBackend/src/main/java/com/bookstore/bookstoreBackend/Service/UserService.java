package com.bookstore.bookstoreBackend.Service;

import com.bookstore.bookstoreBackend.Entity.User;

import java.util.List;

public interface UserService {
    User authenticate(String username, String password);
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUserById(Long id);
}