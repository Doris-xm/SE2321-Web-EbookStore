package org.wordCount.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wordCount.dao.BookDao;
import org.wordCount.entity.Book;
import org.wordCount.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> findBooksByName(String name){
        return bookDao.findBooksByName(name);
    }
    @Override
    public List<Book> findALLBooks(){
        return bookDao.findALLBooks();
    }
    @Override
    public List<Book> findByLabel(String label){
        return bookDao.findByLabel(label);
    }

}
