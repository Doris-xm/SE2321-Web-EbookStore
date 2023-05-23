package com.example.ebook_back.repository;
import com.example.ebook_back.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  BookRepository extends JpaRepository<Book, Integer>{
    Book findBookById(int id);
    List<Book> findAll();

}
