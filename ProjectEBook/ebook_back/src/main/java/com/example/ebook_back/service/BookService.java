package com.example.ebook_back.service;

import com.example.ebook_back.entity.Book;

import java.util.List;

public interface BookService {
    Book findBookById(int id);

    List<Book> getBooks() ;
//    void deleteBooks(List<Integer> bookIds);
    void addBook(Book book);
}
