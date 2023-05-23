package com.example.ebook_back.service;

import com.example.ebook_back.entity.UserAuth;

public interface UserService {
   UserAuth findUserByName(String name);

}
