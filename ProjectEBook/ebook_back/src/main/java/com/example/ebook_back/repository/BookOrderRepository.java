package com.example.ebook_back.repository;

import com.example.ebook_back.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  BookOrderRepository extends JpaRepository<BookOrder, Integer>{
    //    List<BookOrder> findByOrder_OrderID(int orderID);
    List<BookOrder> findByOrderID(int orderID);
    List<BookOrder> findAll();
}
