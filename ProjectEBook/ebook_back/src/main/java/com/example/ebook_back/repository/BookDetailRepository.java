package com.example.ebook_back.repository;

import com.example.ebook_back.entity.BookDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookDetailRepository extends MongoRepository<BookDetail, Integer> {
    List<BookDetail> findBookDetailById(int id);
}
