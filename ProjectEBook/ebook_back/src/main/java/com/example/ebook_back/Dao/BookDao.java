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

package com.example.ebook_back.dao;
import com.example.ebook_back.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface  BookDao extends JpaRepository<Book, Integer>{

    Book findBookById(long id);
    List<Book> findAll();

}
