package com.example.ebook_back.service;

import com.example.ebook_back.entity.BookOrder;
import com.example.ebook_back.entity.MyOrder;

import java.util.List;

public interface OrderService {
    List<MyOrder> findOrderById(int id);
    boolean saveOrder(MyOrder order);

    List<MyOrder> findAll();
    boolean changeState(int orderId, int state);
    List<BookOrder> findAllOrderItems();
}
