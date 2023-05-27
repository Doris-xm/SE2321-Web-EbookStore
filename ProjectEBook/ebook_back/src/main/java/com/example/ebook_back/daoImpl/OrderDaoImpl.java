package com.example.ebook_back.daoImpl;
import com.example.ebook_back.dao.OrderDao;
import com.example.ebook_back.entity.MyOrder;
import com.example.ebook_back.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<MyOrder> findByUserID(int id) {
        return orderRepository.findByUserID(id);
    }

    @Override
    public void saveOrder(MyOrder order) {
        orderRepository.save(order);
    }
    @Override
    public List<MyOrder> findAll(){
        return orderRepository.findAll();
    }
}
