package com.example.ebook_back.dao;
import com.example.ebook_back.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  OrderDao extends JpaRepository<MyOrder, Integer>{
    List<MyOrder> findByUserID(int id);

//    Object save(MyOrder order);
}
