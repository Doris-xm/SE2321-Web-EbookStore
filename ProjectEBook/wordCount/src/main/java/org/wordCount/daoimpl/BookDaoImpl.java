package org.wordCount.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wordCount.dao.BookDao;
import org.wordCount.entity.Book;
import org.wordCount.repository.BookRepository;

import java.util.List;


@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> findBooksByName(String name){
        return bookRepository.findBooksByName(name);
    }
    @Override
    public List<Book> findALLBooks(){
        return bookRepository.findALLBooks();
    }

}
