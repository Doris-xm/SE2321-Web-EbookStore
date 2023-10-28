package org.reins.se3353.book.daoimpl;

import org.reins.se3353.book.dao.BookDao;
import org.reins.se3353.book.entity.Book;
import org.reins.se3353.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> findBooksByName(String name){
        return bookRepository.findBooksByName(name);
    }

}
