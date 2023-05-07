package com.example.ebook_back.dao;
import com.example.ebook_back.entity.MyOrder;
import com.example.ebook_back.entity.User;
import com.example.ebook_back.entity.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface  UserDao extends JpaRepository<UserAuth, Integer>{
    UserAuth findByUserName(String name);
}
