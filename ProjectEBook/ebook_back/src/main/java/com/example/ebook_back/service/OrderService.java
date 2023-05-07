package com.example.ebook_back.service;
import com.example.ebook_back.dao.OrderDao;
import com.example.ebook_back.dao.BookOrderDao;
import com.example.ebook_back.entity.BookOrder;
import com.example.ebook_back.entity.MyOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class OrderService{

    @Autowired
    private OrderDao OrderDao;
    @Autowired
    private BookOrderDao BookOrderDao;
    public List<MyOrder> findOrderById(int id){ //id为用户id
        List<MyOrder> orders = OrderDao.findByUserID(id);
        for(MyOrder order : orders) {
            List<BookOrder> Orders = BookOrderDao.findByOrderID(order.getOrderID());
//            System.out.println(Orders.toString());
            order.setBookOrders(Orders);
        }
        return orders;
    }

    public boolean saveOrder(MyOrder order) {
        try {
//            System.out.println("New Order1:");
            OrderDao.save(order);
//            System.out.println("New Order1:");
            IntStream.range(0, order.getBookOrders().size())
                    .forEach(i -> {
                        BookOrder bookOrder = order.getBookOrders().get(i);
                        System.out.println("New Order1:");
                        bookOrder.setOrderID(order.getOrderID());
                        BookOrderDao.save(bookOrder);
                    });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}