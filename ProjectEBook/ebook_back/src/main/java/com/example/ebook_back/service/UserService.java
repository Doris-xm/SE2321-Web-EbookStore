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

import com.example.ebook_back.dao.UserDao;
import com.example.ebook_back.entity.User;
import com.example.ebook_back.entity.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    @Autowired
    private UserDao userDao;

    public UserAuth findUserByName(String name){
        return userDao.findByUserName(name);
    }

}
