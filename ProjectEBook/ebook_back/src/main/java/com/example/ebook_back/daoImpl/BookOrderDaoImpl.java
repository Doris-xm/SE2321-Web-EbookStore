package com.example.ebook_back.daoImpl;

import com.example.ebook_back.dao.BookOrderDao;
import com.example.ebook_back.entity.BookOrder;
import com.example.ebook_back.repository.BookOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BookOrderDaoImpl implements BookOrderDao {
//    List<BookOrder> findByOrder_OrderID(int orderID);
    @Autowired
    private BookOrderRepository bookOrderRepository;
    @Override
    public List<BookOrder> findByOrderID(int orderID){
        return bookOrderRepository.findByOrderID(orderID);
    }
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void saveBookOrder(BookOrder bookOrder){
        bookOrderRepository.save(bookOrder);
    }
    @Override
    public List<BookOrder> findAll(){
        return bookOrderRepository.findAll();
    }
}
