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

import com.example.ebook_back.dao.UserDao;
import com.example.ebook_back.entity.UserAuth;
import com.example.ebook_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserAuth findUserByName(String name) {
        return userDao.findByUserName(name);
    }

}
