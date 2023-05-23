package com.example.ebook_back.controller;

import com.example.ebook_back.entity.Book;
import com.example.ebook_back.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BookController {
    @Resource
    private BookService bookService;
//    @RequestMapping("/books")
//    public List<Map<String, Object>> getList(){
//        return bookRepository.queryForList("Select * from ebook.book");
//    }
    @RequestMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }
    @RequestMapping("/book")
    public Book getBookById(@RequestParam("id") int id) {
        return  bookService.findBookById(id);
    }
//    @RequestMapping("/")
//    public String home() {
//        String booksString = JSON.toJSONString(getBooks(), SerializerFeature.BrowserCompatible);
//        return booksString;
//    }
}
