package com.example.ebook_back.dao;
import com.example.ebook_back.entity.MyOrder;
import java.util.List;

public interface  OrderDao{
    List<MyOrder> findByUserID(int id);

    void saveOrder(MyOrder order);

    List<MyOrder> findAll();

    boolean changeState(int orderId, int state);

//    Object save(MyOrder order);
}
