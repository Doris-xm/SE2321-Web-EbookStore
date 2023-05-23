package com.example.ebook_back.service;

import com.example.ebook_back.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getCart(int userID);
    boolean addBook(int userID, int bookID);

    boolean changeQuantity(int userID, int bookID,int quantity) ;

    boolean clear(int userID);


}
