package com.bookstore.bookstoreBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bookstoreBackend.Entity.BookStore;



public interface BookRepo extends JpaRepository<BookStore, Long> {

}
