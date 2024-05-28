package com.example.Bookstore.service;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.repository.IBookDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {


    @Autowired
    private final IBookDAO bookDAO;

    public BookService(IBookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    @Transactional
    @Override
    public void saveOrUpdate(Book book) {
        this.bookDAO.saveOrUpdate(book);
    }

    @Transactional
    @Override
    public Optional<Book> getById(int id) {
        return this.bookDAO.getById(id);
    }

    @Transactional
    @Override
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }

    @Transactional
    @Override
    public void delete(int id) {
        bookDAO.delete(id);
    }
}
