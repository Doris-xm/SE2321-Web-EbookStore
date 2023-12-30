package org.wordCount.dao;

import org.wordCount.entity.Book;

import java.util.List;

public interface  BookDao {
    List<Book> findBooksByName(String name);
    List<Book> findALLBooks();
    List<Book> findByLabel(String label);

}
