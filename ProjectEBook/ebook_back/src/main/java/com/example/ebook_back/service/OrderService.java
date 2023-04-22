package com.example.ebook_back.service;
import com.example.ebook_back.dao.OrderDao;
import com.example.ebook_back.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService{

    @Autowired
    private OrderDao OrderDao;
    public List<Order> findOrderById(long id){
        return OrderDao.findByUserID(id);
    }

}