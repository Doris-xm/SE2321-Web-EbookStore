package org.reins.se3353.book.service;


import org.reins.se3353.book.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findBooksByName(String name);
}
