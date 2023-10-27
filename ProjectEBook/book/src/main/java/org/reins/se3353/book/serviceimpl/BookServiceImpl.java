package org.reins.se3353.book.serviceimpl;


import org.reins.se3353.book.dao.BookDao;
import org.reins.se3353.book.entity.Book;
import org.reins.se3353.book.service.BookService;
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

}
