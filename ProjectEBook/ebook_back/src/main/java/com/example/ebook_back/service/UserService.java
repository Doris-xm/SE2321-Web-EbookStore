package com.example.ebook_back.service;

import com.example.ebook_back.entity.User;
import com.example.ebook_back.entity.UserAuth;

import java.util.List;

public interface UserService {
   UserAuth findUserByName(String name);
    void activateUser(int id);
    boolean logout(int id);

    boolean checkSession(int id);
    boolean checkName(String name);
    boolean checkMail(String mail);
    boolean register(String name,String password,String email);
    boolean banUser(int id,boolean ban);
    List<UserAuth> getAllUsers();
    UserAuth findUserAuthById(int id);

}
