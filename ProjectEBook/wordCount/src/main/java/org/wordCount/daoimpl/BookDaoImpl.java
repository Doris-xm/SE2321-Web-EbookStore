package org.wordCount.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wordCount.dao.BookDao;
import org.wordCount.entity.Book;
import org.wordCount.entity.LabelType;
import org.wordCount.repository.BookRepository;
import org.wordCount.repository.LabelTypeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LabelTypeRepository labelTypeRepository;
    @Override
    public List<Book> findBooksByName(String name){
        return bookRepository.findBookByTitle(name);
    }
    @Override
    public List<Book> findALLBooks(){
        return bookRepository.findAll();
    }
    @Override
    public List<Book> findByLabel(String label){
        List<LabelType> labels =  labelTypeRepository.findNeibour(label);
        Set<Book> books = new HashSet<>();
        for (LabelType labelType : labels) {
            List<Book> bookList = bookRepository.findBookByTypeContaining(labelType.getLabel());
            books.addAll(bookList);
        }
        books.addAll(bookRepository.findBookByTypeContaining(label));
        return new ArrayList<>(books);
    }

}
