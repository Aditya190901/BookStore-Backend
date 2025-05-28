package com.bookstore.bookstoreBackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.bookstoreBackend.Entity.BookStore;
import com.bookstore.bookstoreBackend.Entity.User;
import com.bookstore.bookstoreBackend.Service.BookStoreService;
import com.bookstore.bookstoreBackend.Service.UserService;

@RestController
@RequestMapping("/api/bookStore")
@CrossOrigin(origins = "*")
public class BookStoreController {
    private final BookStoreService bookStoreService;
    private final UserService userService;

    public BookStoreController(BookStoreService bookStoreService, UserService userService){
        this.bookStoreService = bookStoreService;
        this.userService = userService;
    }

    @GetMapping("/fetch")
    public List<BookStore> getAllBooks(){
        return bookStoreService.getAllBooks();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User authenticatedUser = userService.authenticate(
            user.getUsername(),
            user.getPassword()
        );
         Map<String, Object> response = new HashMap<>();
        if (authenticatedUser != null) {
            response.put("status", 200);
            response.put("username", authenticatedUser.getUsername());
            response.put("usertype", authenticatedUser.getUsertype());
            response.put("name",authenticatedUser.getName());
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", 401);
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody BookStore bookStore) {
        BookStore savedBook = bookStoreService.saveBook(bookStore);
        Map<String, Object> response = new HashMap<>();
        response.put("status", 201);
        response.put("message", "Book added successfully");
        response.put("book", savedBook);
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            bookStoreService.deleteBookById(id);
            response.put("status", 200);
            response.put("message", "Book deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", 404);
            response.put("message", "Book not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("status", 201);
        response.put("message", "User added successfully");
        response.put("user", savedUser);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        // Remove password before sending response
        users.forEach(user -> user.setPassword(null));
        return ResponseEntity.ok(users);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userService.getUserById(id);
        Map<String, Object> response = new HashMap<>();
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setUsertype(user.getUsertype());
            existingUser.setName(user.getName());
            // Only update password if provided and not empty
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                existingUser.setPassword(user.getPassword());
            }
            User updatedUser = userService.saveUser(existingUser);
            updatedUser.setPassword(null); // Don't send password
            response.put("status", 200);
            response.put("message", "User updated successfully");
            response.put("user", updatedUser);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", 404);
            response.put("message", "User not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            userService.deleteUserById(id);
            response.put("status", 200);
            response.put("message", "User deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", 404);
            response.put("message", "User not found");
            return ResponseEntity.status(404).body(response);
        }
    }
}
