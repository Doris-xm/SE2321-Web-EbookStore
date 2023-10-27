package org.reins.se3353.book.daoimpl;

import org.reins.se3353.book.dao.BookDao;
import org.reins.se3353.book.entity.Book;
import org.reins.se3353.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book findBookById(int id){
        return bookRepository.findBookById(id);
    }

}
