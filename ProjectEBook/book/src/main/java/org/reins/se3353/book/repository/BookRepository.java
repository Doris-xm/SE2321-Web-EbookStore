package org.reins.se3353.book.repository;

import org.reins.se3353.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  BookRepository extends JpaRepository<Book, Integer> {
    Book findBookById(int id);

}

