package com.example.bookManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookManager.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByPriceBetween(Double minPrice, Double maxPrice);
}