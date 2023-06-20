package com.example.ebook_back.dao;
import com.example.ebook_back.entity.Book;

import java.util.List;

public interface  BookDao {
    Book findBookById(int id);
    List<Book> findAll();
    void deleteById(int id);
    void save(Book book);

}
