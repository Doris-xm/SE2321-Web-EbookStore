package com.example.ebook_back.repository;
import com.example.ebook_back.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  OrderRepository extends JpaRepository<MyOrder, Integer>{
    List<MyOrder> findByUserID(int id);
    MyOrder findMyOrderByOrderID(int orderId);

//    Object save(MyOrder order);
}
