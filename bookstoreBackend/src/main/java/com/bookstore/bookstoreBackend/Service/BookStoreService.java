package com.bookstore.bookstoreBackend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookstore.bookstoreBackend.Entity.BookStore;
import com.bookstore.bookstoreBackend.repository.BookRepo;

@Service 
public class BookStoreService {
    private final BookRepo bookRepo;

    public BookStoreService(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }

    public List<BookStore> getAllBooks(){
        return bookRepo.findAll();
    }
    
    public BookStore saveBook(BookStore bookStore) {
        return bookRepo.save(bookStore);
    }

    public void deleteBookById(Long id) {
        bookRepo.deleteById(id);
    }
}
