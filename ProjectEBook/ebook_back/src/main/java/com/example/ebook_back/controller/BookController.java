package com.example.ebook_back.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.ebook_back.entity.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BookController {

    private List<Book> books = Arrays.asList(
            new Book(1L, "name1", "author1", "english", "2022", "100"),
            new Book(2L, "name2", "author2", "chinese", "2023", "200"),
            new Book(3L, "name3", "author3", "japanese", "2024", "300")
    );

    @RequestMapping("/")
    public String home() {
        String booksString = JSON.toJSONString(books, SerializerFeature.BrowserCompatible);
        return booksString;
    }
}
