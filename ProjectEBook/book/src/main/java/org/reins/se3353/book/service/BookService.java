package org.reins.se3353.book.service;


import org.reins.se3353.book.entity.Book;

public interface BookService {
    Book findBookById(int id);
}
