package org.wordCount.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.wordCount.entity.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Integer> {
    List<Book> findBookByTitle(String title);
}
