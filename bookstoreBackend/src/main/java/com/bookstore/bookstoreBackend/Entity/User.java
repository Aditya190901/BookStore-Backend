package com.bookstore.bookstoreBackend.Entity;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String usertype;
    private String name;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUsertype() { return usertype; }
    public void setUsertype(String usertype) { this.usertype = usertype; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
