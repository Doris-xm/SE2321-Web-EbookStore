//package com.example.ebook_back.service;
//import com.example.ebook_back.entity.Book;
//import java.util.List;
//
//
//public interface BookService {
//
//    Book findBookById(Integer id);
//    List<Book> getBooks();
//}
package com.example.ebook_back.service;

import com.example.ebook_back.dao.BookDao;
import com.example.ebook_back.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService{

    @Autowired
    private BookDao bookDao;

    public Book findBookById(long id){
        return bookDao.findBookById(id);
    }

    public List<Book> getBooks() {
        return bookDao.findAll();
    }
}
