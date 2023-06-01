package com.example.ebook_back.dao;
import com.example.ebook_back.entity.User;
import com.example.ebook_back.entity.UserAuth;


public interface  UserDao {
    UserAuth findByUserName(String name);
    void activateUser(int id);
    boolean logout(int id);

    boolean checkSession(int id);
    boolean checkName(String name);
    boolean checkMail(String mail);
    boolean createUser(User user);
    boolean createUserAuth(UserAuth userAuth);
    User findUserByMail(String mail);

}
