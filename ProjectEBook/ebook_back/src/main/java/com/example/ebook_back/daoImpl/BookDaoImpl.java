//package com.example.ebook_back.dao;
//
//import com.example.ebook_back.entity.Book;
//
//import java.util.List;
//
//public interface BookDao {
//    Book findOne(Integer id);
//    List<Book> getBooks();
//}

package com.example.ebook_back.daoImpl;
import com.example.ebook_back.dao.BookDao;
import com.example.ebook_back.entity.Book;
import com.example.ebook_back.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findBookById(int id){
        return bookRepository.findBookById(id);
    }

    @Override
    public List<Book> findAll(){
        List<Book> books = bookRepository.findAll();
        books.removeIf(book -> book.getStocks() < 0);
        return books;
    }

//    @Override
//    public void deleteById(int id){
//        Book book = bookRepository.findBookById(id);
//        if(book == null){
//            throw(new RuntimeException("Book not found"));
//        }
//        bookRepository.removeBookById(id);
//    }
    @Override
    public void save(Book book){
        bookRepository.save(book);
    }

}
