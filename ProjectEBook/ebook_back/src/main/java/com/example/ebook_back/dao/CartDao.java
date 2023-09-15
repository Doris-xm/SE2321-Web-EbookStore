package com.example.ebook_back.dao;
import com.example.ebook_back.entity.Cart;
import java.util.List;


public interface  CartDao {
    List<Cart> findAllByUserID(int userID);
    Cart findByBookIDAndUserID(int bookID,int userID);

    void deleteAll(List<Cart> cart);

    void saveCartItem(Cart exist);

    void deleteCartItem(Cart exist);
}
