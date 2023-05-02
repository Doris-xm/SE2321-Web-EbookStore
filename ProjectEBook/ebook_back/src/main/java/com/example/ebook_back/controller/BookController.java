package com.example.ebook_back.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.ebook_back.entity.Book;
import com.example.ebook_back.service.BookService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


// //previous implement
//package com.example.ebook_back.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.example.ebook_back.entity.Book;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@RestController
//public class BookController {
//
//    @GetMapping("/books")
//    public List<Book> getAllBooks() {
//        List<Book> books = new ArrayList<>();
//        books.add(new Book(1L, "The Great Gatsby", "F. Scott Fitzgerald", 9.99F,"https://s1.ax1x.com/2023/04/06/ppIWwC9.jpg"));
//        books.add(new Book(2L, "To Kill a Mockingbird", "Harper Lee", 8.99F,"https://s1.ax1x.com/2023/04/06/ppIWgED.jpg"));
//        books.add(new Book(3L, "The Catcher in the Rye", "J. D. Salinger", 7.99F,"https://s1.ax1x.com/2023/04/06/ppIfTzR.png"));
//        books.add(new Book(4L, "The Grapes of Wrath", "John Steinbeck", 16.99F,"https://s1.ax1x.com/2023/04/06/ppIfTzR.png"));
//        books.add(new Book(5L, "The Lord of the Rings", "J. R. R. Tolkien", 25.99F,"https://s1.ax1x.com/2023/04/06/ppIfTzR.png"));
//        books.add(new Book(6L, "The Hobbit", "J. R. R. Tolkien", 24.99F,"https://s1.ax1x.com/2023/04/06/ppIfTzR.png"));
//        books.add(new Book(7L, "The Adventures of Huckleberry Finn", "Mark Twain", 13.99F,"https://s1.ax1x.com/2023/04/06/ppIfTzR.png"));
//        books.add(new Book(8L, "The Kite Runner", "Khaled Hosseini", 20.99F,"https://s1.ax1x.com/2023/04/06/ppIfTzR.png"));
//        return books;
//    }
//
//
//    @RequestMapping("/")
//    public String home() {
//        String booksString = JSON.toJSONString(getAllBooks(), SerializerFeature.BrowserCompatible);
//        return booksString;
//    }
//}
