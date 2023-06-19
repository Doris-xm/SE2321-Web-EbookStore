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
import com.example.ebook_back.entity.User;
import com.example.ebook_back.entity.UserAuth;
import com.example.ebook_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserAuth findUserByName(String name) {
        return userDao.findByUserName(name);
    }
    @Override
    public void activateUser(int id){
        userDao.activateUser(id);
    }
    @Override
    public boolean logout(int id){
        return userDao.logout(id);
    }

    @Override
    public boolean checkSession(int id) {
        return userDao.checkSession(id);
    }
    @Override
    public boolean checkName(String name){
        return userDao.checkName(name);
    }
    @Override
    public boolean checkMail(String mail){
        return userDao.checkMail(mail);
    }
    @Override
    public boolean resign(String name,String password,String email){
        if(!checkName(name)){
            return false;
        }
        if(!checkMail(email)){
            return false;
        }
        User user = new User();
        user.setEmail(email);
        user.setNickname(name);
        user.setAvatar("https://pic2.zhimg.com/v2-76a083febca664209097391ed4750769_r.jpg");
        if( !userDao.createUser(user)){
            return false;
        }
        User savedUser = userDao.findUserByMail(email);
        System.out.println(savedUser);
        UserAuth userAuth = new UserAuth();
        userAuth.setUserName(name);
        userAuth.setPassword(password);
        userAuth.setBan(false);
        userAuth.setUserMode(0);
//        userAuth.setUser(savedUser);
        System.out.println(savedUser.getId());
        userAuth.setUserId(savedUser.getId());
        return userDao.createUserAuth(userAuth);
    }
    @Override
    public boolean banUser(int id,boolean ban){
        return userDao.banUser(id,ban);
    }
    @Override
    public List<UserAuth> getAllUsers(){
        return userDao.getAllUsers();
    }
    @Override
    public UserAuth findUserAuthById(int id){
        return userDao.findUserAuthById(id);
    }
}
