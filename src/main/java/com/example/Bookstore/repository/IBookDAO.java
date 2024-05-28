package com.example.Bookstore.repository;

import com.example.Bookstore.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    void saveOrUpdate(Book book);
    Optional<Book> getById(int id);
    List<Book> getAll();
    void delete(int id);
}
