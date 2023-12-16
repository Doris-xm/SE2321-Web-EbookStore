package org.wordCount.service;



import org.wordCount.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findBooksByName(String name);
    List<Book> findALLBooks();
}
