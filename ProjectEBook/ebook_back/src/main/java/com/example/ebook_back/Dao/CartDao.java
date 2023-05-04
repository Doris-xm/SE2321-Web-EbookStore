package com.example.ebook_back.dao;
import com.example.ebook_back.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  CartDao extends JpaRepository<Cart, Integer>{
    List<Cart> findAllByUserID(int userID);
    Cart findByBookIDAndUserID(int bookID,int userID);
}
