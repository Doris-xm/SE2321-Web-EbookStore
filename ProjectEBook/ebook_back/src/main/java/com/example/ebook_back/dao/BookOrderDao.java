package com.example.ebook_back.dao;

import com.example.ebook_back.entity.BookOrder;

import java.util.List;


public interface BookOrderDao{

     List<BookOrder> findByOrderID(int orderID);

     void saveBookOrder(BookOrder bookOrder);
     List<BookOrder> findAll();
}
