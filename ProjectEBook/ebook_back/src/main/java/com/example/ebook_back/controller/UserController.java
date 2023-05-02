package com.example.ebook_back.controller;

import com.example.ebook_back.entity.User;
import com.example.ebook_back.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/user")
    public User getUserById(@RequestParam("name") String name) {
        return  userService.findUserByName(name);
    }

}
