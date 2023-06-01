package com.example.ebook_back.service;

import com.example.ebook_back.entity.UserAuth;

public interface UserService {
   UserAuth findUserByName(String name);
    void activateUser(int id);
    boolean logout(int id);

    boolean checkSession(int id);
    boolean checkName(String name);
    boolean checkMail(String mail);
    boolean resign(String name,String password,String email);

}
