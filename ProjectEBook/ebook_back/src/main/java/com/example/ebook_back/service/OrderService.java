package com.example.ebook_back.service;

import com.example.ebook_back.entity.MyOrder;

import java.util.List;

public interface OrderService {
    List<MyOrder> findOrderById(int id);
    boolean saveOrder(MyOrder order);
}
