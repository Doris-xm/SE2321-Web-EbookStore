package com.example.ebook_back.daoImpl;
import com.example.ebook_back.dao.OrderDao;
import com.example.ebook_back.entity.Book;
import com.example.ebook_back.entity.BookOrder;
import com.example.ebook_back.entity.MyOrder;
import com.example.ebook_back.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
    @Transactional(Transactional.TxType.REQUIRED)
    public void saveOrder(MyOrder order) {
        orderRepository.save(order);
    }
    @Override
    public List<MyOrder> findAll(){
        return orderRepository.findAll();
    }
    @Override
    public boolean changeState(int orderId, int state){
        try {
            MyOrder order = orderRepository.findMyOrderByOrderID(orderId);
            if(order == null){
                return false;
            }
            order.setState(state);
            orderRepository.save(order);
            return true;
        }catch ( Exception e) {
            return false;
        }

    }
}
