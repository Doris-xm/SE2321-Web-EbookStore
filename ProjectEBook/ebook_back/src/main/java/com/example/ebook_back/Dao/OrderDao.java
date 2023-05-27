package com.example.ebook_back.dao;
import com.example.ebook_back.entity.MyOrder;
import java.util.List;

public interface  OrderDao{
    List<MyOrder> findByUserID(int id);

    void saveOrder(MyOrder order);

    List<MyOrder> findAll();

//    Object save(MyOrder order);
}
