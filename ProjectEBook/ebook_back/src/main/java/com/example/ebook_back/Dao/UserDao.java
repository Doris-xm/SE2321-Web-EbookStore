package com.example.ebook_back.dao;
import com.example.ebook_back.entity.UserAuth;


public interface  UserDao {
    UserAuth findByUserName(String name);
}
