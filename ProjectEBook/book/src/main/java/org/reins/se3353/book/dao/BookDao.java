package org.reins.se3353.book.dao;

import org.reins.se3353.book.entity.Book;

import java.util.List;

public interface  BookDao {
    List<Book> findBooksByName(String name);

}
