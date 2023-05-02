package com.example.ebook_back.dao;

import com.example.ebook_back.entity.BookOrder;
import com.example.ebook_back.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface  BookOrderDao extends JpaRepository<BookOrder, Integer>{
    List<BookOrder> findByOrder_OrderID(int orderID);
}
