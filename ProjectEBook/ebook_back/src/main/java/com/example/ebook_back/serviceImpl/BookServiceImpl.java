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
package com.example.ebook_back.serviceImpl;

import com.example.ebook_back.dao.BookDao;
import com.example.ebook_back.entity.Book;
import com.example.ebook_back.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(int id){
        return bookDao.findBookById(id);
    }
    @Override
    public List<Book> getBooks() {
        return bookDao.findAll();
    }
    @Override
    public void deleteBooks(List<Integer> bookIds) {
        try {
            for (int bookId : bookIds) {
                bookDao.deleteById(bookId);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    @Override
    public void addBook(Book book) {
        try {
            bookDao.save(book);
        } catch (Exception e) {
            throw e;
        }
    }
}
