package com.example.ebook_back.serviceImpl;
import com.example.ebook_back.dao.OrderDao;
import com.example.ebook_back.dao.BookOrderDao;
import com.example.ebook_back.entity.BookOrder;
import com.example.ebook_back.entity.MyOrder;
import com.example.ebook_back.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private BookOrderDao bookOrderDao;

    @Override
    public List<MyOrder> findOrderById(int id){ //id为用户id
        List<MyOrder> orders = orderDao.findByUserID(id);
        for(MyOrder order : orders) {
            List<BookOrder> Orders = bookOrderDao.findByOrderID(order.getOrderID());
//            System.out.println(Orders.toString());
            order.setBookOrders(Orders);
        }
        return orders;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean saveOrder(MyOrder order) {
        try {
            orderDao.saveOrder(order); // 保存订单到数据库
            IntStream.range(0, order.getBookOrders().size())
                    .forEach(i -> {
                        BookOrder bookOrder = order.getBookOrders().get(i);
                        System.out.println("New Order1:");
                        bookOrder.setOrderID(order.getOrderID());
                        bookOrderDao.saveBookOrder(bookOrder); // 保存订单项到数据库
                    });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public List<MyOrder> findAll() {
//        return orderDao.findAll();
        List<MyOrder> orders = orderDao.findAll();
        for(MyOrder order : orders) {
            List<BookOrder> Orders = bookOrderDao.findByOrderID(order.getOrderID());
//            System.out.println(Orders.toString());
            order.setBookOrders(Orders);
        }
        return orders;

    }

    @Override
    public boolean changeState(int orderId, int state) {
        return orderDao.changeState(orderId, state);
    }
    @Override
    public List<BookOrder> findAllOrderItems() {
        return bookOrderDao.findAll();
    }

}